package com.app.worldkids.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.Settings
import java.util.UUID

@SuppressLint("HardwareIds")
private fun getDeviceId(resolver: ContentResolver): String {
    val deviceId = Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID)
    return UUID.nameUUIDFromBytes(deviceId.toByteArray()).toString()
}

val getAndroidID get() = getDeviceId(ContextApp.applicationContext.contentResolver)