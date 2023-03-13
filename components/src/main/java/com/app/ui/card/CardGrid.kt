package com.app.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.app.domain.model.Card
import com.app.ui.theme.BaseTheme
import com.app.ui.theme.SupportScreenSize
import com.app.ui.theme.color3E9346

@Composable
fun CardGrid(
    cards: List<Card>,
    onClick: ((Card) -> Unit)? = null,
) {
    LazyVerticalGrid(
        state = rememberLazyGridState(),
        columns = GridCells.Fixed(8),
        contentPadding = PaddingValues(all = SupportScreenSize.dimens.dimens_24),
        horizontalArrangement = Arrangement.spacedBy(SupportScreenSize.dimens.dimens_27),
        verticalArrangement = Arrangement.spacedBy(SupportScreenSize.dimens.dimens_24)
    ) {
        items(items = cards, key = { it.name }) { card ->
            CardGridItem(
                card = card,
                onClick = onClick,
            )
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
        CardGrid(ConstantPreviewCard.CARD_LIST)
    }
}
