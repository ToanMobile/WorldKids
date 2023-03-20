package com.app.worldkids.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("platform")
    val platform: String = "android-tv",
    @SerialName("deviceId")
    val deviceId: String?= null,
)
