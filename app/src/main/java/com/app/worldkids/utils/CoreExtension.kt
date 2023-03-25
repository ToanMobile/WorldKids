package com.app.worldkids.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.app.worldkids.R
import com.app.worldkids.model.StatusType
import com.google.android.material.imageview.ShapeableImageView
import timber.log.Timber
import java.util.UUID

@SuppressLint("HardwareIds")
private fun getDeviceId(resolver: ContentResolver): String {
    val deviceId = Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID)
    return UUID.nameUUIDFromBytes(deviceId.toByteArray()).toString()
}

fun Drawable.tint(context: Context, @ColorRes color: Int) {
    DrawableCompat.setTint(this, context.resources.getColor(color, context.theme))
}

@BindingAdapter("setStatus")
fun ShapeableImageView.setStatus(isStatus: String) {
    this.strokeColor = when (isStatus) {
        StatusType.IN.name -> ColorStateList.valueOf(context.getColor(R.color.color3E9346))
        StatusType.ABSENT.name -> ColorStateList.valueOf(context.getColor(R.color.colorEA1911))
        StatusType.LATE.name -> ColorStateList.valueOf(context.getColor(R.color.color8939DA))
        StatusType.OFF.name -> ColorStateList.valueOf(context.getColor(R.color.colorF7AD1A))
        StatusType.CONFIRM_OFF.name -> ColorStateList.valueOf(context.getColor(R.color.colorF27F0C))
        else -> ColorStateList.valueOf(context.getColor(R.color.color3E9346))
    }
}

@BindingAdapter("setStatus")
fun TextView.setStatus(isStatus: String) {
    this.background = when (isStatus) {
        StatusType.IN.name -> AppCompatResources.getDrawable(context, R.color.color3E9346)
        StatusType.ABSENT.name -> AppCompatResources.getDrawable(context, R.color.colorEA1911)
        StatusType.LATE.name -> AppCompatResources.getDrawable(context, R.color.color8939DA)
        StatusType.OFF.name -> AppCompatResources.getDrawable(context, R.color.colorF7AD1A)
        StatusType.CONFIRM_OFF.name -> AppCompatResources.getDrawable(context, R.color.colorF27F0C)
        else -> AppCompatResources.getDrawable(context, R.color.color3E9346)
    }
}

val getAndroidID get() = getDeviceId(ContextApp.applicationContext.contentResolver)

fun withDialogItems(view: View, context: Context) {
    val items = arrayOf("Đã có mặt", "Xác nhận vắng")
    val popupMenu = PopupMenu(context, view)
    popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
    popupMenu.show()
}