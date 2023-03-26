package com.app.worldkids.model.response


import com.idanatz.oneadapter.external.interfaces.Diffable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckIn(
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
) : Diffable {
    override val uniqueIdentifier: Long = client?.id?.toLong() ?: 0L
    override fun areContentTheSame(other: Any): Boolean = status == (other as CheckIn).status
    override fun toString(): String {
        return "CheckIn(client=$client, status=$status)"
    }
}