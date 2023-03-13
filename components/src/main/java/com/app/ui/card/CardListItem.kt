package com.app.ui.card

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.domain.model.Card
import com.app.ui.theme.SupportScreenSize
import com.app.ui.theme.color3E9346
import com.app.ui.theme.colorWhite

@Composable
fun CardListItem(
    card: Card,
    onClick: ((Card) -> Unit)? = null,
) {
    val borderColor = remember { mutableStateOf(color3E9346) }
    Card(
        modifier = Modifier
            .width(SupportScreenSize.dimens.dimens_206)
            .height(SupportScreenSize.dimens.dimens_275)
            .onFocusEvent {
                Log.e("isFocused:", it.isFocused.toString())
            }
            .onFocusChanged {
                Log.e("isFocused1:", it.isFocused.toString())
            },
        shape = RoundedCornerShape(SupportScreenSize.dimens.dimens_8),
        elevation = CardDefaults.cardElevation(1.dp),
        border = BorderStroke(SupportScreenSize.dimens.dimens_4, borderColor.value),
    ) {
        Box(modifier = Modifier
            .background(colorWhite)
            .fillMaxSize()
            .align(alignment = Alignment.CenterHorizontally)
            .clickable { onClick?.invoke(card) })
        {
            CardItem(card = card, isBig = true)
        }
    }
}