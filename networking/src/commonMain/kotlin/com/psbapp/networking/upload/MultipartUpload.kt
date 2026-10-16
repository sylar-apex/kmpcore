package com.psbapp.networking.upload

import com.psbapp.networking.client.NetworkClient
import com.psbapp.networking.client.NetworkState
import com.psbapp.networking.response.BaseResponse
import com.psbapp.networking.utils.BaseProgressBarState
import com.psbapp.networking.utils.checkStatus
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Uploads one file to [url] and parses the envelope's `data` as [T].
 *
 * [fields] go up as plain text parts next to the file (folder, title…). [onProgress] reports bytes
 * sent / total for a progress bar. Emits Loading → Success | Error → Loading(Idle), like every
 * other `NetworkClient` flow.
 */
suspend inline fun <reified T> NetworkClient.ktorUploadFlowRequest(
    url: String,
    file: UploadFile,
    fieldName: String = UploadSpec.DEFAULT_FIELD,
    fields: Map<String, String> = emptyMap(),
    noinline onProgress: ((sent: Long, total: Long) -> Unit)? = null,
): Flow<NetworkState<BaseResponse<T>>> = flow {
    emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
    emit(uploadOnce<T>(url, file, fieldName, fields, onProgress))
    emit(NetworkState.Loading(BaseProgressBarState.Idle))
}

/**
 * Uploads every file in [files] to [url], one request each and in order, and hands back the parsed
 * `data` of each as a list in the same order. Stops at the first failure and emits its message, so
 * a caller never acts on a half-uploaded set. [onProgress] reports the file index being sent.
 */
suspend inline fun <reified T> NetworkClient.ktorUploadListFlowRequest(
    url: String,
    files: List<UploadFile>,
    fieldName: String = UploadSpec.DEFAULT_FIELD,
    fields: Map<String, String> = emptyMap(),
    noinline onProgress: ((index: Int, sent: Long, total: Long) -> Unit)? = null,
): Flow<NetworkState<List<T>>> = flow {
    emit(NetworkState.Loading(BaseProgressBarState.ShimmerLoading))
    val results = ArrayList<T>(files.size)
    var failure: String? = null
    for ((index, file) in files.withIndex()) {
        val progress = onProgress?.let { report -> { sent: Long, total: Long -> report(index, sent, total) } }
        when (val state = uploadOnce<T>(url, file, fieldName, fields, progress)) {
            is NetworkState.Success -> {
                val data = state.response.data
                if (data == null) {
                    failure = state.response.message.ifBlank { UploadSpec.FAILED_MESSAGE }
                } else {
                    results += data
                }
            }
            is NetworkState.Error -> failure = state.message
            is NetworkState.Loading -> Unit
        }
        if (failure != null) break
    }
    emit(if (failure == null) NetworkState.Success(results.toList()) else NetworkState.Error(failure))
    emit(NetworkState.Loading(BaseProgressBarState.Idle))
}

/** The single request both public helpers share. Never throws; a failure comes back as Error. */
@PublishedApi
internal suspend inline fun <reified T> NetworkClient.uploadOnce(
    url: String,
    file: UploadFile,
    fieldName: String,
    fields: Map<String, String>,
    noinline onProgress: ((sent: Long, total: Long) -> Unit)?,
): NetworkState<BaseResponse<T>> = try {
    val response = ktorClient.getHttpClient(true).submitFormWithBinaryData(
        url = url,
        formData = formData {
            fields.forEach { (key, value) -> append(key, value) }
            append(
                fieldName,
                file.bytes,
                Headers.build {
                    append(HttpHeaders.ContentType, file.contentType)
                    append(HttpHeaders.ContentDisposition, "filename=\"${file.fileName}\"")
                },
            )
        },
    ) {
        if (onProgress != null) {
            onUpload { sent, total -> if (total != null && total > 0) onProgress(sent, total) }
        }
    }
    if (checkStatus(response.status)) {
        NetworkState.Success(response.body<BaseResponse<T>>())
    } else {
        NetworkState.Error(response.body<BaseResponse<String>>().message.ifBlank { UploadSpec.FAILED_MESSAGE })
    }
} catch (e: Exception) {
    NetworkState.Error(e.message ?: UploadSpec.FAILED_MESSAGE)
}
