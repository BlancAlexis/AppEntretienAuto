package com.example.manageyourcar.dataLayer.cache.carDatastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object CarPreferencesSerializer : Serializer<CarCache> {
    override val defaultValue: CarCache
        get() = CarCache()

    override suspend fun readFrom(input: InputStream): CarCache {
        return try {
            Json.decodeFromString(
                deserializer = CarCache.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: CarCache, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = CarCache.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}


