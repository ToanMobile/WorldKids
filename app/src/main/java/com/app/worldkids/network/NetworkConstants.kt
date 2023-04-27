package com.app.worldkids.network

object NetworkConstants {
    const val baseUrl = "https://api-ccare.baonguyengroup.com.vn/"

    object WorldKids {
        const val route = baseUrl + "api"
        const val register = "$route/admin/auth/login-device"
        fun listCheckIn(classId: String) = "$route/admin/checkin/$classId/list-checkin"
        fun listCheckOut(classId: String) = "$route/admin/checkin/$classId/list-checkout"

        fun statusReport(classId: String, type:String) = "$route/admin/checkin/$classId/report?type=$type"

        fun verify(classId: String) = "$route/admin/checkin/$classId/confirm-checkin"

        val changeStatus = "$route/admin/checkin/change-status"

        val byName: (String) -> String = { name -> "$route/$name"}
    }
}