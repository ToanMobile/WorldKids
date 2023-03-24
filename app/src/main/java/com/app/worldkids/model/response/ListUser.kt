package com.app.worldkids.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListUser(
    @SerialName("listCheckin")
    val listCheckin: List<CheckIn>? = null,
    @SerialName("listNotCheckin")
    val listNotCheckin: List<CheckIn>? = null
) {
    override fun toString(): String {
        return "ListUser(listCheckin=$listCheckin, listNotCheckin=$listNotCheckin)"
    }
}