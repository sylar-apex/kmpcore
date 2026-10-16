package com.psbapp.networking.utils


import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

inline fun <reified T> T.toRequestMapper(
    ignoreIsEmpty: Boolean = true,
    ignoreIsNull: Boolean = true
): String where T : Any {
    val json = Json { encodeDefaults = false }
    val jsonObject = json.encodeToJsonElement(
        serializer(),
        this
    ).jsonObject // Convert to JsonObject
    val filteredJsonObject = jsonObject.filterNot { (key, value) ->
        (ignoreIsNull && value.isJsonNull()) || (ignoreIsEmpty && value.isEmptyJson())
    }
    return json.encodeToString(JsonObject(filteredJsonObject)) // Return as JSON string
}

// Extension to check if a JsonElement is null
fun JsonElement?.isJsonNull(): Boolean = this == null || this.toString() == "null"

// Extension to check if a JsonElement is empty
fun JsonElement.isEmptyJson(): Boolean {
    return when (this) {
        is JsonArray -> this.isEmpty()
        is JsonObject -> this.isEmpty()
        else -> this.toString().trim('"').isEmpty()
    }
}

// Extension to convert JsonElement to primitive or list
fun JsonElement.toPrimitive(): Any {
    return when (this) {
        is JsonArray -> this.map { it.toPrimitive() }
        is JsonObject -> this.map { it.key to it.value.toPrimitive() }.toMap() // Nested objects
        else -> this.toString().trim('"') // Handle primitive types
    }
}


fun Any?.toJsonElement(): JsonElement = when (this) {
    null -> JsonNull
    is JsonElement -> this
    is Number -> JsonPrimitive(this)
    is Boolean -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is Array<*> -> JsonArray(map { it.toJsonElement() })
    is List<*> -> JsonArray(map { it.toJsonElement() })
    is Map<*, *> -> JsonObject(map { it.key.toString() to it.value.toJsonElement() }.toMap())
    else -> this.toString().toJsonElement()
}

fun Any?.toJsonString(): String = Json.encodeToString(this.toJsonElement())

