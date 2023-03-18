package com.app.worldkids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("image")
    val image: String?= null,
    @SerialName("deviceToken")
    val deviceToken: String?= null,
)
