package com.app.presentation.scenes.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.app.ui.theme.SupportScreenSize
import com.app.ui.theme.color3968DA
import com.app.ui.theme.color3E9346
import com.app.ui.theme.color8939DA
import com.app.ui.theme.color999999
import com.app.ui.theme.colorDCF1DD
import com.app.ui.theme.colorEA1911
import com.app.ui.theme.colorF27F0C
import com.app.ui.theme.colorF7AD1A
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
            Column {
                HeaderTitle(currentTime = currentTime.value)
                Divider()
                HeaderCheckIn()
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

@Composable
private fun HeaderTitle(currentTime: LocalDateTime) {
    Box(modifier = Modifier.padding(SupportScreenSize.dimens.dimens_24)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Lớp Mickey 01 - WorldKids Bình Tân", maxLines = 1, style = SupportScreenSize.textStyle.text36Bold)
                Row {
                    Text(text = "Giáo viên phụ trách:", maxLines = 1, style = SupportScreenSize.textStyle.text20Regular)
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_4))
                    Text(text = "Nguyễn Thị Kim Anh", maxLines = 1, style = SupportScreenSize.textStyle.text20Bold.copy(color = color3E9346))
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_16))
                    Text(text = "Bảo Mẫu:", maxLines = 1, style = SupportScreenSize.textStyle.text20Regular)
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_4))
                    Text(text = "Nguyễn Như Ngọc", maxLines = 1, style = SupportScreenSize.textStyle.text20Bold.copy(color = color3E9346))
                }
            }
            Column {
                Text(text = "Điểm danh vào lớp", maxLines = 1, style = SupportScreenSize.textStyle.text14Bold)
                Text(text = "Đã xác nhận ", maxLines = 1, style = SupportScreenSize.textStyle.text14Bold)
            }
            Column {
                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    maxLines = 1,
                    textAlign = TextAlign.End,
                    style = SupportScreenSize.textStyle.text48Bold.copy(color3E9346)
                )
                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")),
                    maxLines = 1,
                    style = SupportScreenSize.textStyle.text24Regular.copy(color3E9346)
                )
            }
        }
    }
}

@Composable
private fun HeaderCheckIn() {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(SupportScreenSize.dimens.dimens_80)
            .background(colorDCF1DD)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(text = "Đã có mặt", style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "10", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = color3E9346))
            }
            Column {
                Text(text = "Vắng có phép", style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = colorF7AD1A))
            }
            Column {
                Text(text = "Xác nhận vắng", style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = colorF27F0C))
            }
            Column {
                Text(text = "Xin vào trễ", style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = color8939DA))
            }
            Column {
                Text(text = "Đang vắng mặt", style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "2", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = colorEA1911))
            }
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
