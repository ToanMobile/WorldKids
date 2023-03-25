package com.app.worldkids.utils

import android.graphics.drawable.PaintDrawable

class RoundedRectDrawable(color: Int, radius: Float) : PaintDrawable(color) {
    init {
        setCornerRadius(radius)
    }
}