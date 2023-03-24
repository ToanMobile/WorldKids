package com.app.worldkids.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("fullname")
    val fullname: String? = null,
    @SerialName("id")
    val id: Int? = null
) {
    override fun toString(): String {
        return "User(avatar=$avatar, email=$email, fullname=$fullname, id=$id)"
    }
}