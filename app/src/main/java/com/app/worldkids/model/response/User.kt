package com.app.worldkids.model.response


import com.app.worldkids.model.ListMode
import com.idanatz.oneadapter.external.interfaces.Diffable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("className")
    val className: String? = null,
    @SerialName("client")
    val client: Client? = null,
    @SerialName("endTime")
    val endTime: String? = null,
    @SerialName("startTime")
    val startTime: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("timerId")
    val timerId: String? = null
): Diffable {
    override val uniqueIdentifier: Long = 0
    override fun areContentTheSame(other: Any): Boolean = className == (other as User).className
}