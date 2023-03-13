package com.app.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.app.domain.model.Card
import com.app.ui.theme.SupportScreenSize

@Composable
fun CardList(
    cards: List<Card>,
    onClick: ((Card) -> Unit)? = null,
) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        state = lazyListState,
        contentPadding = PaddingValues(all = SupportScreenSize.dimens.dimens_24),
        horizontalArrangement = Arrangement.spacedBy(SupportScreenSize.dimens.dimens_27)
    ) {
        items(items = cards, key = { it.name }) { card ->
            CardListItem(
                card = card,
                onClick = onClick,
            )
        }
    }
}
