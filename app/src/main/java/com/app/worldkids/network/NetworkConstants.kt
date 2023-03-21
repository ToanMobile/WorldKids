package com.app.worldkids.network

object NetworkConstants {
    const val baseUrl = "http://139.180.155.164:3002/"

    object Wordkids {
        const val route = baseUrl + "api"
        const val register = "$route/admin/auth/login-device"
        fun listCheckIn(classId: String) = "$route/admin/checkin/$classId/list-checkin"
        fun listCheckOut(classId: String) = "$route/admin/checkin/$classId/list-checkout"
        val byName: (String) -> String = { name -> "$route/$name"}
    }
}