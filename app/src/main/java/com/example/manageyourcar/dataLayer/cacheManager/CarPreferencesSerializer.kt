
package com.example.manageyourcar.dataLayer.cacheManager

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object CarPreferencesSerializer : Serializer<CarSerializableDatastoreClass> {
    override val defaultValue: CarSerializableDatastoreClass
        get() = CarSerializableDatastoreClass()

    override suspend fun readFrom(input: InputStream): CarSerializableDatastoreClass {
        return try {
            Json.decodeFromString(
                deserializer = CarSerializableDatastoreClass.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: CarSerializableDatastoreClass, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = CarSerializableDatastoreClass.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}


