package com.app.worldkids.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.provider.Settings
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.MenuCompat
import androidx.databinding.BindingAdapter
import com.app.worldkids.R
import com.app.worldkids.model.StatusType
import com.google.android.material.imageview.ShapeableImageView
import me.saket.cascade.CascadePopupMenu
import me.saket.cascade.allChildren
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
        StatusType.IN.name -> {
            text = this.context.getString(R.string.checkIn)
            AppCompatResources.getDrawable(context, R.color.color3E9346)
        }

        StatusType.ABSENT.name -> {
            text = this.context.getString(R.string.noCheckIn)
            AppCompatResources.getDrawable(context, R.color.colorEA1911)
        }

        StatusType.LATE.name -> {
            text = this.context.getString(R.string.confirm_delay)
            AppCompatResources.getDrawable(context, R.color.color8939DA)
        }

        StatusType.OFF.name -> {
            text = this.context.getString(R.string.off)
            AppCompatResources.getDrawable(context, R.color.colorF7AD1A)
        }

        StatusType.CONFIRM_OFF.name -> {
            text = this.context.getString(R.string.confirm_off)
            AppCompatResources.getDrawable(context, R.color.colorF27F0C)
        }

        else -> {
            text = this.context.getString(R.string.present)
            AppCompatResources.getDrawable(context, R.color.color3E9346)
        }
    }
}

val getAndroidID get() = getDeviceId(ContextApp.applicationContext.contentResolver)

fun withDialogItems(view: View, context: Context, callback: (Int) -> Unit) {
    val popupMenu = CascadePopupMenu(context, view, styler = cascadeMenuStyler(context = context))
    popupMenu.menu.apply {
        MenuCompat.setGroupDividerEnabled(this, true)
        add(context.getString(R.string.present))
        add(context.getString(R.string.confirm_off))
        allChildren.filter { it.intent == null }.forEach { item ->
            item.setOnMenuItemClickListener {
                callback(it.itemId)
                popupMenu.navigateBack()
            }
        }
    }
    popupMenu.show()
}

private fun cascadeMenuStyler(context: Context): CascadePopupMenu.Styler {
    val rippleDrawable = {
        RippleDrawable(ColorStateList.valueOf(Color.parseColor("#FF3E9346")), null, ColorDrawable(BLACK))
    }

    return CascadePopupMenu.Styler(
        background = { RoundedRectDrawable(Color.parseColor("#FFFFFFFF"), radius = 8f) },
        menuTitle = {
            it.setBackground(rippleDrawable())
        },
        menuItem = {
            if (it.layoutPosition == 1) {
                it.titleView.setTextColor(context.getColor(R.color.colorF27F0C))
            } else {
                it.titleView.setTextColor(context.getColor(R.color.color3E9346))
            }
            it.setBackground(rippleDrawable())
            it.setGroupDividerColor(Color.parseColor("#FF3E9346"))
        }
    )
}