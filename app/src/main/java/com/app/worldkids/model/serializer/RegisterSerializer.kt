package com.app.worldkids.model.serializer

import androidx.datastore.core.Serializer
import com.app.worldkids.model.response.Register
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

object RegisterSerializer : Serializer<Register?> {

    override val defaultValue: Register
        get() = Register()

    override suspend fun readFrom(input: InputStream): Register? {
        return try {
            Json.decodeFromString(Register.serializer(), input.readBytes().decodeToString())
        } catch (serialization: SerializationException) {
            Timber.e("readFrom: err: ${serialization.message}")
            defaultValue
        }
    }

    override suspend fun writeTo(t: Register?, output: OutputStream) {
        try {
            t?.let {
                withContext(Dispatchers.IO) {
                    output.write(
                        Json.encodeToString(Register.serializer(), t).encodeToByteArray()
                    )
                }
            }
        } catch (ex: Exception) {
            Timber.e("writeTo: ${ex.message}")
        }
    }
}
