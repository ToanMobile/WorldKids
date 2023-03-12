package com.app.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.domain.model.Card
import com.app.ui.theme.BaseTheme
import com.app.ui.theme.SupportScreenSize
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CardList(
    cards: List<Card>,
    isRefresh: Boolean = false,
    onClick: ((Card) -> Unit)? = null,
    onCheck: ((Card) -> Unit)? = null,
    onRefresh: (() -> Unit)? = null
) {
    var isRefreshing by remember { mutableStateOf(false) }
    isRefreshing = isRefresh
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            onRefresh?.invoke()
        },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(state, trigger)
        },
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
                    onCheck = false
                )
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
        CardList(ConstantPreviewCard.CARD_LIST)
    }
}
