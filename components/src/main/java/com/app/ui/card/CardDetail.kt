package com.app.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.domain.model.Card
import com.app.domain.model.TypeEnum
import com.app.ui.R
import com.app.ui.theme.BaseTheme
import com.app.ui.theme.SupportScreenSize

@Composable
fun CardDetail(
    card: Card,
    openLink: ((String) -> Unit)? = null
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(card.picture),
                    modifier = Modifier
                        .blur(radius = 16.dp)
                        .fillMaxWidth()
                        .height(330.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Background Card ${card.name}"
                )
            }
            Image(
                painter = rememberAsyncImagePainter(card.picture),
                contentDescription = "Card ${card.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(290.dp)
                    .aspectRatio(350f / 495f)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
        Button(
            onClick = { openLink?.invoke(card.wikiLink) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 6.dp)
        ) {
            Text("More information".uppercase())
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Wiki",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = card.name,
                    maxLines = 1,
                    style = SupportScreenSize.textStyle.text24Bold
                )
                card.japaneseName?.let {
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        text = it,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = SupportScreenSize.textStyle.text14Bold
                    )
                }
                card.frenchName?.let {
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        text = it,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = SupportScreenSize.textStyle.text14Bold
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Pokedex N° ${card.pokemonNumber}",
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = SupportScreenSize.textStyle.text14Bold
                )
                Spacer(modifier = Modifier.size(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.draw),
                        contentDescription = "Illustrator",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = card.illustrator,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = SupportScreenSize.textStyle.text14Bold
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    CardRarity(rarityEnum = card.rarity)
                    if (card.type != TypeEnum.TRAINER && card.type != TypeEnum.ENERGY) {
                        Spacer(modifier = Modifier.weight(0.8f))
                        CardType(typeEnum = card.type)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
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
        CardDetail(ConstantPreviewCard.CARD)
    }
}
