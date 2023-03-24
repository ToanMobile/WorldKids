package com.app.worldkids.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Register(
    @SerialName("auth")
    val auth: Auth? = null,
    @SerialName("data")
    val data: DataX? = null
) {

    @Serializable
    data class Auth(
        @SerialName("expired")
        val expired: String? = null, // 20-09-2023 08:20:32
        @SerialName("token")
        val token: String? = null // eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI1IiwianRpIjoiZjNkYTIyN2UyZDJhNTgwMTJhMmUzZTVkZjQ4YTI2Mjk3OWRkNTQ2NzdiZDk4ZGNhMDVlNmRiOWNiZDRjN2MwMTMwN2E3OTk3NDk3ZDVkNzgiLCJpYXQiOjE2NzkzMDA0MzMuMDA0MTEyLCJuYmYiOjE2NzkzMDA0MzMuMDA0MTI0LCJleHAiOjE2OTUxOTgwMzIuOTk0MDg5LCJzdWIiOiI3Iiwic2NvcGVzIjpbXX0.GspxLcFQDvWGXNZnT5yqVhMS9-RorVsR2sqSNH6veD8BqSyMWTc7lYr-hOQpeEG5g2z6sjNZnap-vBLDK9PpnHYtkWpq1YKvg10PI1vHjAlgNJGGP3XGjJjZNmhMvCTG2nJt08Uds7Xh1OfrjkTGXvVbO98vd42Y2Tb13TrcHdbxEavG6RiivfP6nb3n5qIjhpKZs6H8-oNZcfm4qsQNI4zA3lttzUj-yWJYbtNAOsaJ0EKZtyQxHq2mnbixl8mvCxnLBoc5UGMcgcNYu-XJzQRYBviMD-AKCwV27uhjqPFtSkoE7rsApTfb5Eah_SAqgXgmunnEEbJQxFuYgyDHgTwuRa47dwZJr52vBRvkvIDQeauRl95419UdsZbKfP9OG6Y7U7SHF_ltES0LvwVzJz9dhblg6SPQk7RdBgWYcZL3BR95Jr70zPc_NbTCmnjsaTlEzlUc2y7ms_Ms0tfDMhd-X7-wvAT-EEE4vF8Acobri_lXWjsXhhkJBWM9QA7AbV1KEx_4KPDtSoTI8VxS2s9mvL_cUBwnh7lyl10UmOQFwY8mvaFrDYC6f4JRBcYiU4d30eozNDoTruLfq5oWxsP29b66Ok4qyDeYq9EtZp8yeVG6T3fLh6tabMDkzG1kQPrkmZeykGSi19PmivYrGlbYIAQwp678TvgCattMk6U
    ) {
        override fun toString(): String {
            return "Auth(expired=$expired, token=$token)"
        }
    }

    @Serializable
    data class DataX(
        @SerialName("class")
        val classX: Class,
        @SerialName("deviceId")
        val deviceId: String,
        @SerialName("platform")
        val platform: String,
        @SerialName("school")
        val school: School
    ) {
        override fun toString(): String {
            return "DataX(classX=$classX, deviceId=$deviceId, platform=$platform, school=$school)"
        }
    }
}