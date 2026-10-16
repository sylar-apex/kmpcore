package com.psbapp.networking.response

/**
 * Status values the backend uses to say "the call went through, the operation did not" — a http 200
 * body such as `{"status":"failed","message":"The amount must be at least 100."}`. Those arrive as
 * `NetworkState.Success`, so the ViewModel has to check the envelope before acting on the payload.
 *
 * Deliberately a whitelist of failure words rather than "anything that is not success": the older
 * endpoints answer with `statuscode: "TXN"` and no `status` at all, so a blank or unknown status
 * has to keep counting as success.
 */
private val FAILURE_STATUSES = setOf("failed", "failure", "error", "false")

/** True when the envelope reports a business failure despite a 2xx response. */
fun BaseResponse<*>.isFailed(): Boolean = status.trim().lowercase() in FAILURE_STATUSES

/** Message to surface for a failed response, with a fallback when the backend sends none. */
fun BaseResponse<*>.failureMessage(fallback: String = "Something went wrong"): String =
    message.trim().ifEmpty { fallback }
