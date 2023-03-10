package com.app.ui.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.app.ui.base.Error
import com.app.ui.base.Progress
import com.app.ui.base.SnackMessage
import com.app.ui.base.Toolbar
import com.app.ui.extensions.clearSnackMessage
import com.app.ui.theme.BaseTheme
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderUiState(
    uiStateFlow: MutableStateFlow<UiState>,
    topAppBarTitle: String? = null,
    pressOnBack: (() -> Unit)? = null,
    retry: (() -> Unit)? = null,
    menuActions: @Composable RowScope.() -> Unit = {},
    header: @Composable () -> Unit,
    content: @Composable ((UiState.Content<*>) -> Unit)? = null
) {
    val uiState = uiStateFlow.collectAsState().value
    Scaffold(
        /*topBar = {
            Toolbar(
                title = topAppBarTitle,
                pressOnBack = pressOnBack,
                menuActions = menuActions
            )
        },*/
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                header()
                when (uiState) {
                    is UiState.Loading -> Progress()
                    is UiState.Error -> Error(
                        errorMessage = uiState.message,
                        isLoading = uiState.isLoading,
                        retry = retry
                    )
                    is UiState.Content<*> -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            content?.invoke(uiState)
                            uiState.snackMessage?.let { message ->
                                SnackMessage(message = message, onDismiss = {
                                    uiStateFlow.clearSnackMessage()
                                })
                            }
                        }
                    }
                }
            }
        },
        modifier = Modifier.semantics { testTag = "content_view" }
    )
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
        RenderUiState(MutableStateFlow(UiState.Content("Test")), header = {})
    }
}
