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
    override val uniqueIdentifier: Long = 0
    override fun areContentTheSame(other: Any): Boolean = className == (other as CheckIn).className
    override fun toString(): String {
        return "CheckIn(className=$className, client=$client, endTime=$endTime, startTime=$startTime, status=$status, timerId=$timerId, uniqueIdentifier=$uniqueIdentifier)"
    }
}