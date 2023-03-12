package com.app.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.domain.model.Card
import com.app.ui.theme.BaseTheme
import com.app.ui.theme.SupportScreenSize
import com.app.ui.theme.color3E9346
import com.app.ui.theme.color8939DA
import com.app.ui.theme.colorWhite

@Composable
fun CardGridItem(
    card: Card,
    onClick: ((Card) -> Unit)? = null,
    onCheck: Boolean
) {
    Card(
        modifier = Modifier
            .width(SupportScreenSize.dimens.dimens_206)
            .height(SupportScreenSize.dimens.dimens_206),
        shape = RoundedCornerShape(SupportScreenSize.dimens.dimens_8),
        elevation = CardDefaults.cardElevation(SupportScreenSize.dimens.dimens_4),
        border = if (onCheck) BorderStroke(SupportScreenSize.dimens.dimens_4, color3E9346) else CardDefaults.outlinedCardBorder(enabled = false),
    ) {
        Box(modifier = Modifier
            .background(colorWhite)
            .fillMaxSize()
            .align(alignment = Alignment.CenterHorizontally)
            .clickable { onClick?.invoke(card) })
        {
            CardItem(card = card)
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview() {
    BaseTheme {
        CardGridItem(ConstantPreviewCard.CARD, onCheck = false)
    }
}
