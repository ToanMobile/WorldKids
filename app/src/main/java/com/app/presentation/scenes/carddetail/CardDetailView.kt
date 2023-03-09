package com.app.presentation.scenes.carddetail

import com.app.presentation.scenes.base.BaseUiStateView
import kotlinx.coroutines.flow.Flow

interface CardDetailView : BaseUiStateView {

    fun intentLoadData(): Flow<String>

    fun intentErrorRetry(): Flow<String>
}
