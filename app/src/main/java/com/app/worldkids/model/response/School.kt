package com.app.worldkids.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class School(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
) {
    override fun toString(): String {
        return "School(id=$id, name=$name)"
    }
}