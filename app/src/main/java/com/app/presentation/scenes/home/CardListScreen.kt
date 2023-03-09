package com.app.presentation.scenes.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.domain.model.Card
import com.app.presentation.scenes.base.PublishSharedFlow
import com.app.ui.card.CardGrid
import com.app.ui.card.CardList
import com.app.ui.extensions.clearSnackMessage
import com.app.ui.extensions.filterDefault
import com.app.ui.extensions.toContent
import com.app.ui.state.RenderUiState
import com.app.ui.state.UiState
import com.app.ui.theme.colorBlue
import com.app.ui.theme.colorGreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.getViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Intents
private val refreshIntent = PublishSharedFlow.create<Unit>()
private val retryIntent = PublishSharedFlow.create<Unit>()
private val checkIntent = PublishSharedFlow.create<Card>()

@Composable
fun CardListScreen(openCard: (Card) -> Unit) {
    val view = remember { createView() }
    val viewModel = getViewModel<CardListViewModel>()
    val currentTime = remember { mutableStateOf<LocalDateTime>(LocalDateTime.now()) }
    LaunchedEffect(key1 = "CardListScreen") {
        viewModel.attach(view)
        currentTime.value = LocalDateTime.now()
    }

    RenderUiState(
        uiStateFlow = view.uiStateFlow,
        retry = { retryIntent.tryEmit(Unit) },
        header = {
            Box(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Lớp Mickey 01 - WorldKids Bình Tân", maxLines = 1, style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold))
                        Row {
                            Text(text = "Giáo viên phụ trách:", maxLines = 1, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal))
                            Text(text = "Nguyễn Thị Kim Anh", maxLines = 1, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold, color = colorGreen))
                            Text(text = "Bảo Mẫu:", maxLines = 1, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal))
                            Text(text = "Nguyễn Như Ngọc", maxLines = 1, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold, color = colorGreen))
                        }
                    }
                    Column {
                        Text(text = "Điểm danh vào lớp", maxLines = 1, style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold))
                        Text(text = "Đã xác nhận ", maxLines = 1, style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold))
                    }
                    Column {
                        Text(
                            text = currentTime.value.format(DateTimeFormatter.ofPattern("HH:mm")),
                            maxLines = 1,
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = currentTime.value.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")),
                            maxLines = 1,
                            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Normal, color = colorBlue)
                        )
                    }
                }
            }
        }
    ) { uiState ->
        uiState.toContent { cards: List<Card> ->
            CardList(
                cards = cards,
                isRefresh = uiState.isRefresh,
                onClick = {
                    view.uiStateFlow.clearSnackMessage()
                    openCard(it)
                },
                onCheck = { checkIntent.tryEmit(it) },
                onRefresh = { refreshIntent.tryEmit(Unit) }
            )
        }
    }
}

private fun createView() = object : CardListView {
    override val uiStateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading.createDefault())

    override fun intentLoadData(): Flow<Unit> = uiStateFlow.filterDefault().map { }

    override fun intentRefreshData(): Flow<Unit> = refreshIntent

    override fun intentErrorRetry(): Flow<Unit> = retryIntent

    override fun intentCheck(): Flow<Card> = checkIntent
}
