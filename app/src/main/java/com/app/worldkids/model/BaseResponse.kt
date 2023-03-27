package com.app.worldkids.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("meta")
    val meta: Meta = Meta(),
    @SerialName("data")
    val `data`: T? = null
) {
    @Serializable
    data class Meta(
        @SerialName("code")
        val code: Int? = 0, // 200
        @SerialName("message")
        val message: String? = "" // ok
    )
}

