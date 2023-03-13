package com.app.ui.dpad.item

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.shape.MaterialShapeDrawable

class SelectionOverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val materialBackground = MaterialShapeDrawable()

    init {
        materialBackground.setCornerSize(8f)
        materialBackground.strokeColor = AppCompatResources.getColorStateList(
            context,
            android.R.color.darker_gray
        )
        materialBackground.fillColor = ColorStateList.valueOf(Color.TRANSPARENT)
        materialBackground.strokeWidth = 4f
        materialBackground.elevation = 2f
        background = materialBackground
    }

}
