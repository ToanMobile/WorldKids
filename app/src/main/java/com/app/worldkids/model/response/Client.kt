package com.app.worldkids.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("birthday")
    val birthday: String? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("fullname")
    val fullname: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("personalId")
    val personalId: String? = null
) {
    override fun toString(): String {
        return "Client(code=$code, fullname=$fullname)"
    }
}