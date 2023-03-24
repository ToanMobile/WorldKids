package com.app.worldkids.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Class(
    @SerialName("assistant")
    val assistant: List<User>? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("incharge")
    val incharge: List<User>? = null,
    @SerialName("manager")
    val user: List<User>? = null,
    @SerialName("name")
    val name: String? = null
) {
    override fun toString(): String {
        return "Class(assistant=$assistant, id=$id, incharge=$incharge, user=$user, name=$name)"
    }
}