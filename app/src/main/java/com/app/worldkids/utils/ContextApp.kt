package com.app.worldkids.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextApp {
    private var INSTANCE: Context? = null
    val applicationContext: Context
        get() = INSTANCE?.applicationContext!!

    fun initContext(context: Context) {
        if (INSTANCE == null) {
            synchronized(ContextApp::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = context
                }
            }
        }
    }
}
