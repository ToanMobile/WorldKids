package com.app.worldkids.network

object NetworkConstants {
    const val baseUrl = "http://139.180.155.164:3002/"

    object Wordkids {
        const val route = baseUrl + "api"
        const val register = "$route/auth/token"
        val byName: (String) -> String = { name -> "$route/$name"}
    }
}