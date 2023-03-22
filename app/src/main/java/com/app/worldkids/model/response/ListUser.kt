package com.app.worldkids.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListUser(
    @SerialName("listCheckin")
    val listCheckin: List<User>? = null,
    @SerialName("listNotCheckin")
    val listNotCheckin: List<User>? = null
)