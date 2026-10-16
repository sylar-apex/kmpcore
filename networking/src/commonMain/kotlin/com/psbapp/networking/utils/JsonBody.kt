package com.psbapp.networking.utils

import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

/**
 * Serializes a request and drops every null field, so one nullable dto can serve an endpoint where
 * a key must be *absent* rather than null.
 *
 * The shared client config keeps `explicitNulls = true` — a null property goes out as
 * `"key": null` — so the trimming happens here instead.
 *
 * The result is ready-to-send [TextContent], not a `JsonObject`: handing a `JsonObject` to Ktor's
 * content negotiation makes it look up serializers by the runtime class of each value
 * (`JsonLiteral`), which works on the JVM and fails on Kotlin/JS with "Serializer … not found".
 */
inline fun <reified T> T.asJsonBodyWithoutNulls(): TextContent {
    val trimmed = JsonObject(
        Json { encodeDefaults = true }
            .encodeToJsonElement(this)
            .jsonObject
            .filterValues { it !is JsonNull }
    )
    return TextContent(Json.encodeToString(JsonObject.serializer(), trimmed), ContentType.Application.Json)
}
