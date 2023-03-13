package com.app.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.domain.model.Card
import com.app.ui.theme.SupportScreenSize
import com.app.ui.theme.color8939DA

@Composable
fun CardItem(
    card: Card,
    isBig: Boolean = false,
    onClick: ((Card) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Box(
            modifier = Modifier
                .width(if (isBig) SupportScreenSize.dimens.dimens_150 else SupportScreenSize.dimens.dimens_100)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Image(
                painter = rememberAsyncImagePainter(card.picture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isBig) SupportScreenSize.dimens.dimens_160 else SupportScreenSize.dimens.dimens_100)
                    .clip(CircleShape)
                    .border(3.dp, color8939DA, CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = "Card ${card.name}"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SupportScreenSize.dimens.dimens_27)
                    .align(alignment = Alignment.BottomCenter)
                    .background(color = color8939DA, shape = RoundedCornerShape(SupportScreenSize.dimens.dimens_8))
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "Xin vào trễ ${card.pokemonNumber}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = SupportScreenSize.textStyle.text10Bold
                )
            }
        }
        Spacer(modifier = Modifier.size(SupportScreenSize.dimens.dimens_24))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Trần Lê Việt Hoàng${card.pokemonNumber}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = SupportScreenSize.textStyle.text12Bold
        )
        Spacer(modifier = Modifier.size(SupportScreenSize.dimens.dimens_8))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Curot ${card.pokemonNumber}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = SupportScreenSize.textStyle.text10Bold
        )
    }
}
