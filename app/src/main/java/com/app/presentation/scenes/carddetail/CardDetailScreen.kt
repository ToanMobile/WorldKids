package com.app.presentation.scenes.carddetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.app.domain.model.Card
import com.app.presentation.scenes.base.PublishSharedFlow
import com.app.ui.base.MenuIcon
import com.app.ui.card.CardDetail
import com.app.ui.extensions.toContent
import com.app.ui.state.RenderUiState
import com.app.ui.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.getViewModel

// Intents
private val retryIntent = PublishSharedFlow.create<Unit>()

@Composable
fun CardDetailScreen(
    cardName: String,
    pressOnBack: (() -> Unit)? = null,
    openLink: ((String) -> Unit)? = null
) {
    val view = remember { createView(cardName) }
    val viewModel = getViewModel<CardDetailViewModel>()
    LaunchedEffect(cardName) { viewModel.attach(view) }

    RenderUiState(
        uiStateFlow = view.uiStateFlow,
        topAppBarTitle = cardName,
        pressOnBack = pressOnBack,
        retry = { retryIntent.tryEmit(Unit) },
        menuActions = {
            MenuIcon(
                imageVector = Icons.Default.Link,
                contentDescription = "Open wiki"
            ) {
                view.uiStateFlow.value.toContent<Card> { card ->
                    openLink?.invoke(card.wikiLink)
                }
            }
        },
        header = {}
    ) { uiState ->
        uiState.toContent { card: Card ->
            CardDetail(card = card, openLink = openLink)
        }
    }
}

private fun createView(cardName: String) = object : CardDetailView {
    override val uiStateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading.createDefault())

    override fun intentLoadData(): Flow<String> =
        flowOf(cardName)

    override fun intentErrorRetry(): Flow<String> =
        retryIntent.map { cardName }
}
