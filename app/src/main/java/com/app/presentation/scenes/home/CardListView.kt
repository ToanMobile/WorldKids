package com.app.presentation.scenes.home

import com.app.domain.model.Card
import com.app.presentation.scenes.base.BaseUiStateView
import kotlinx.coroutines.flow.Flow

interface CardListView : BaseUiStateView {

    fun intentLoadData(): Flow<Unit>

    fun intentRefreshData(): Flow<Unit>

    fun intentErrorRetry(): Flow<Unit>

    fun intentCheck(): Flow<Card>
}
