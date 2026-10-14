package com.psbapp.utils.json

import com.psbapp.utils.EMPTY
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

/** The one shared [Json] configuration. Also installed on the Ktor client's ContentNegotiation. */
val JSON = Json {
    explicitNulls = false
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
    isLenient = true
    useAlternativeNames = true
}

/**
 * A request body whose key names are only known at runtime and so cannot be named by a data class.
 * Goes on the wire as a flat JSON object, every value a string.
 *
 * This is a class rather than a bare [JsonObject] on purpose. `NetworkClient` takes its body as `Any`,
 * so Ktor has no static type and guesses a serializer from the runtime class. A [JsonObject] is a
 * `Map`, so the guess becomes a map serializer and then fails on the values with
 * `Serializer for class 'JsonLiteral' is not found`. A dedicated class resolves to
 * [JsonBodySerializer] instead.
 */
@Serializable(with = JsonBodySerializer::class)
data class JsonBody(val fields: Map<String, String>)

/** Build a [JsonBody] from a map of string fields. */
fun Map<String, String>.toJsonBody(): JsonBody = JsonBody(this)

object JsonBodySerializer : KSerializer<JsonBody> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("JsonBody")

    override fun serialize(encoder: Encoder, value: JsonBody) {
        val jsonEncoder = encoder as JsonEncoder
        jsonEncoder.encodeJsonElement(
            JsonObject(value.fields.mapValues { (_, field) -> JsonPrimitive(field) })
        )
    }

    override fun deserialize(decoder: Decoder): JsonBody =
        throw UnsupportedOperationException("Request only")
}

object KsonUtil {

    val json = JSON

    // ========== Object <-> JSON String ==========

    inline fun <reified T> fromJson(jsonString: String): T? {
        return try {
            json.decodeFromString(jsonString)
        } catch (e: SerializationException) {
            e.printStackTrace()
            null
        }
    }

    inline fun <reified T> toJson(obj: T): String {
        return try {
            json.encodeToString(obj)
        } catch (e: SerializationException) {
            e.printStackTrace()
            String.EMPTY
        }
    }

    // ========== Object <-> JsonElement ==========

    inline fun <reified T> T.toJsonElement(): JsonElement? {
        return try {
            json.encodeToJsonElement(this)
        } catch (e: SerializationException) {
            e.printStackTrace()
            null
        }
    }

    inline fun <reified T> JsonElement.toObject(): T? {
        return try {
            json.decodeFromJsonElement(this)
        } catch (e: SerializationException) {
            e.printStackTrace()
            null
        }
    }

    // ========== JSON String <-> Map<String, String> ==========

    fun String.toMapOfStrings(): Map<String, String> {
        return try {
            val jsonObject = json.decodeFromString<JsonObject>(this)
            jsonObject.mapValues { it.value.toString().trim('"') }
        } catch (e: SerializationException) {
            e.printStackTrace()
            emptyMap()
        }
    }

    // ========== JSON String <-> Map<String, JsonElement> ==========

    fun String.toMapOfJsonElements(): Map<String, JsonElement> {
        return try {
            json.decodeFromString<JsonObject>(this).toMap() ?: emptyMap()
        } catch (e: SerializationException) {
            e.printStackTrace()
            emptyMap()
        }
    }

    // ========== Object <-> Map<String, JsonElement> ==========
    /*
    inline fun <reified T> T.toMap(): Map<String, JsonElement>? {
        return try {
            val jsonElement = json.encodeToJsonElement(this)
            val map: Map<String, JsonElement>? = (jsonElement as? JsonObject)?.toMap() ?: emptyMap()
            map
        } catch (e: SerializationException) {
            e.printStackTrace()
            null
        }
    }*/

    inline fun <reified T> mapToObject(map: Map<String, JsonElement>): T? {
        return try {
            val jsonObject = JsonObject(map)
            json.decodeFromJsonElement(jsonObject)
        } catch (e: SerializationException) {
            e.printStackTrace()
            null
        }
    }
}

/*
* @Serializable
data class User(val name: String, val age: Int)

val user = User("Alice", 30)

// Object → JSON string
val jsonString = JsonUtil.toJson(user)

// JSON string → Object
val user2 = JsonUtil.fromJson<User>(jsonString!!)

// Object → Map<String, JsonElement>
val userMap = user.toMap()

// Map<String, JsonElement> → Object
val userFromMap = JsonUtil.mapToObject<User>(userMap!!)

// JSON string → Map<String, String>
val strMap = jsonString.toMapOfStrings()

// JSON string → Map<String, JsonElement>
val jsonElementMap = jsonString.toMapOfJsonElements()
* */
