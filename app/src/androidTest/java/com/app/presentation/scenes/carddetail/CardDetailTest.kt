package com.app.presentation.scenes.carddetail

import androidx.compose.ui.test.junit4.createComposeRule
import com.app.domain.model.Card
import com.app.domain.model.RarityEnum
import com.app.domain.model.TypeEnum
import com.app.ui.card.CardDetail
import com.app.ui.extensions.toContent
import com.app.ui.state.RenderUiState
import com.app.ui.state.UiState
import com.app.ui.theme.BaseTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CardDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val uiStateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading.createDefault())

    @Before
    fun setup() {
        composeTestRule.setContent {
            BaseTheme {
                RenderUiState(uiStateFlow) { uiState ->
                    uiState.toContent { card: Card ->
                        CardDetail(card)
                    }
                }
            }
        }
    }

    private fun robot(func: CardDetailRobot.() -> Unit) {
        CardDetailRobot.robot(composeTestRule, uiStateFlow, func)
    }

    @Test
    fun showData() = runTest {
        val card = Card(
            "Charizard",
            "Dracaufeu",
            "リザードン",
            TypeEnum.FIRE,
            RarityEnum.RARE_HOLO,
            6,
            "https://lopspower.github.io/poke/img/BaseSet16.webp",
            "Mitsuhiro Arita",
            "https://bulbapedia.bulbagarden.net/wiki/Charizard_(Base_Set_4)",
            16,
            true
        )

        robot {
            loading {
                content()
                progress()
                viewError(false)
                errorProgress(false)
            }
            data(card) {
                content()
                data(card)
                progress(false)
                viewError(false)
                errorProgress(false)
            }
        }
    }

    @Test
    fun showErrorAndRetry() {
        val myError = "My error"

        robot {
            loading {
                content()
                progress()
            }
            error(myError) {
                viewError()
                textError(myError)
                errorProgress(false)
                content(false)
                progress(false)
            }
            retry(myError) {
                viewError()
                errorProgress()
                textError(myError)
                content(false)
                progress(false)
            }
        }
    }
}
