package com.psbapp.networking.client

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.path

fun HttpRequestBuilder.GET(
    endpointUrl: String,
    parameters: Map<String, Any> = emptyMap()
) {
    method = HttpMethod.Get
    url {
        path(endpointUrl)
        parameters.forEach { (key, value) ->
            parameter(
                key,
                value
            )
        }
    }
}

fun HttpRequestBuilder.GET(
    url: String,
    parameters: List<Pair<String, Any>> = emptyList()
) {
    method = HttpMethod.Get
    url {
        path(url)
        parameters.forEach { item ->
            parameter(
                item.first,
                item.second
            )


        }
    }
}

inline fun <reified T : Any> HttpRequestBuilder.POST(
    endpointUrl: String,
    parameters: Map<String, Any> = emptyMap(),
    body: T?
) {

    method = HttpMethod.Post
    body?.run { setBody(this) }
    url {
        path(endpointUrl)
        parameters.forEach { (key, value) ->
            parameter(
                key,
                value
            )
        }
    }
}


inline fun <reified T : Any> HttpRequestBuilder.PUT(
    endpointUrl: String,
    parameters: Map<String, Any> = emptyMap(),
    body: T?
) {
    method = HttpMethod.Put
    body?.run { setBody(this) }
    url {
        path(endpointUrl)
        parameters.forEach { (key, value) ->
            parameter(
                key,
                value
            )
        }
    }
}

inline fun <reified T : Any> HttpRequestBuilder.DELETE(
    endpointUrl: String,
    parameters: Map<String, Any> = emptyMap(),
    body: T?
) {
    method = HttpMethod.Delete
    body?.run { setBody(this) }
    url {
        path(endpointUrl)
        parameters.forEach { (key, value) ->
            parameter(
                key,
                value
            )
        }
    }
}

/**
 * Use this method to send form data. Content Type is ("application", "x-www-form-urlencoded")
 */
fun HttpRequestBuilder.POSTFormData(
    endpointUrl: String,
    parameters: Map<String, String> = emptyMap(),
) {
    url {
        path(endpointUrl)
        // appends parameters in url.parameters.
        parameters.forEach { (key, value) ->
            parameter(
                key,
                value
            )
        }
        val formParameters = url.parameters.build()
        method = HttpMethod.Post
        setBody(FormDataContent(formParameters))
    }

}

fun HttpRequestBuilder.POSTMultiPartFormData(
    endpointUrl: String,
    parameters: Map<String, String> = emptyMap(),
) {
    url {
        path(endpointUrl)
        // appends parameters in url.parameters.
        val formParameters = url.parameters.build()
        method = HttpMethod.Post
        setBody(MultiPartFormDataContent(parts = formData {
            parameters.forEach { (key, value) ->
                append(
                    key,
                    value
                )
            }
        }))
    }

}