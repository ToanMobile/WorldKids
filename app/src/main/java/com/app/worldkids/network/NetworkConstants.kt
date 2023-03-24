package com.app.worldkids.network

object NetworkConstants {
    const val baseUrl = "http://139.180.155.164:3002/"

    object Wordkids {
        const val route = baseUrl + "api"
        const val register = "$route/admin/auth/login-device"
        fun listCheckIn(classId: String) = "$route/admin/checkin/$classId/list-checkin"
        fun listCheckOut(classId: String) = "$route/admin/checkin/$classId/list-checkout"

        fun statusReport(classId: String, type:String) = "$route/admin/checkin/$classId/report?type=$type"

        val changeStatus = "$route/admin/checkin/change-status"

        val byName: (String) -> String = { name -> "$route/$name"}
    }
}