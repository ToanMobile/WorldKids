package com.app.presentation.scenes.home

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.app.domain.model.Card
import com.app.presentation.R
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
    val scrollState = rememberScrollState()

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
            Box(
                modifier = Modifier
                    .scrollable(state = scrollState, orientation = Orientation.Vertical)
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = SupportScreenSize.dimens.dimens_40,
                        end = SupportScreenSize.dimens.dimens_40,
                        top = SupportScreenSize.dimens.dimens_24
                    )
                ) {
                    Text(stringResource(id = R.string.noCheckIn), style = SupportScreenSize.textStyle.text14Regular.copy(color = color999999))
                    CardList(
                        cards = cards,
                        onClick = {
                            view.uiStateFlow.clearSnackMessage()
                            openCard(it)
                        },
                    )
                    Text(stringResource(id = R.string.checkIn), style = SupportScreenSize.textStyle.text14Regular.copy(color = color999999))
                    CardGrid(
                        cards = cards,
                        onClick = {
                            view.uiStateFlow.clearSnackMessage()
                            openCard(it)
                        },
                    )
                }
            }
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
                    Text(text = stringResource(id = R.string.teacher), maxLines = 1, style = SupportScreenSize.textStyle.text20Regular)
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_4))
                    Text(text = "Nguyễn Thị Kim Anh", maxLines = 1, style = SupportScreenSize.textStyle.text20Bold.copy(color = color3E9346))
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_16))
                    Text(text = stringResource(id = R.string.nanny), maxLines = 1, style = SupportScreenSize.textStyle.text20Regular)
                    Spacer(modifier = Modifier.width(SupportScreenSize.dimens.dimens_4))
                    Text(text = "Nguyễn Như Ngọc", maxLines = 1, style = SupportScreenSize.textStyle.text20Bold.copy(color = color3E9346))
                }
            }
            Column {
                Text(text = stringResource(id = R.string.verify_in_class), maxLines = 1, style = SupportScreenSize.textStyle.text14Bold)
                Text(text = stringResource(id = R.string.verify), maxLines = 1, style = SupportScreenSize.textStyle.text14Bold)
            }
            Column(modifier = Modifier.wrapContentWidth(align = Alignment.End), horizontalAlignment = Alignment.End) {
                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    maxLines = 1,
                    style = SupportScreenSize.textStyle.text48Bold.copy(color3968DA)
                )
                Text(
                    text = currentTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")),
                    maxLines = 1,
                    style = SupportScreenSize.textStyle.text24Regular.copy(color3968DA)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(start = SupportScreenSize.dimens.dimens_16, top = SupportScreenSize.dimens.dimens_4),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.present), style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "10", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = color3E9346))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.off_), style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = colorF7AD1A))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.confirm_off), style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = colorF27F0C))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.confirm_delay), style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
                Text(text = "1", style = SupportScreenSize.textStyle.text26Bold.copy(fontWeight = FontWeight.SemiBold, color = color8939DA))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.off), style = SupportScreenSize.textStyle.text14SemiBold.copy(color = color999999))
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
