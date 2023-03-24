package com.app.worldkids.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeStatusRequest(
    @SerialName("clientId")
    val clientId: String?= null,
    @SerialName("status")
    val status: String?= null,
)
