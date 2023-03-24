package com.app.worldkids.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckInStatus(
    @SerialName("totalCheckin")
    val totalCheckin: Int = 0,
    @SerialName("totalOff")
    val totalOff: Int = 0,
    @SerialName("totalConfirmOff")
    val totalConfirmOff: Int = 0,
    @SerialName("totalLate")
    val totalLate: Int = 0,
    @SerialName("totalAbsent")
    val totalAbsent: Int = 0,
)