package com.app.presentation.scenes.base

import com.app.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Copyright (C) 2023 ToanMobile
 * Licensed under the Apache License Version 2.0
 * Interface representing a View that will use to load data.
 */
interface BaseUiStateView {

    val uiStateFlow: MutableStateFlow<UiState>
}
