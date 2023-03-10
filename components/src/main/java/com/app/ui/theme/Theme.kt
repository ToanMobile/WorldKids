package com.app.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.ui.R
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.text.Typography

object SupportScreenSize {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
    val textStyle: TypographyApp
        @Composable
        get() = LocalAppTypography.current
}


@Composable
fun ProvideDimens(dimensions: Dimensions, content: @Composable () -> Unit) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf { normalDimension }

@Composable
fun ProvideAppTypography(typography: TypographyApp, content: @Composable () -> Unit) {
    val typographySet = remember { typography }
    CompositionLocalProvider(LocalAppTypography provides typographySet, content = content)
}

private val LocalAppTypography = staticCompositionLocalOf { textNormalDimension }


private val LightColors = lightColorScheme(
    primary = Color(0xFF644887),
    surfaceVariant = Color(0xFF594078),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFFf9aa33),
    onSurfaceVariant = Color(0xFFe0992d),
    onSecondary = Color(0xFF000000),

    background = Color(0xFFFFFDF5),
    onBackground = Color(0xFF222222),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),

    error = Color(0xFFbf324c),
    onError = Color(0xFFFFFFFF)
)

private val DarkColors = lightColorScheme(
    primary = Color(0xFF705097),
    surfaceVariant = Color(0xFF644887),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFFf9aa33),
    onSurfaceVariant = Color(0xFFe0992d),
    onSecondary = Color(0xFF000000),

    background = Color(0xFF242424),
    onBackground = Color(0xFFFFFFFF),

    surface = Color(0xFF313131),
    onSurface = Color(0xFFFFFFFF),

    error = Color(0xFFbf324c),
    onError = Color(0xFFFFFFFF)
)

@Composable
fun BaseTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val colors = if (darkTheme) DarkColors else LightColors
    val smallestScreen = LocalConfiguration.current.smallestScreenWidthDp
    Log.e("smallestScreen::",smallestScreen.toString())
    val dimensions = when (smallestScreen) {
        in 2100..2150 -> D2100Dimension
        else -> normalDimension
    }
    val typography = when (smallestScreen) {
        in 2100..2150 -> text2100Dimension
        else -> textNormalDimension
    }
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isSystemBarsVisible = false
    ProvideDimens(dimensions = dimensions) {
        ProvideAppTypography(typography = typography) {
            MaterialTheme(
                colorScheme = colors,
                typography = chatTypography,
                content = content
            )
        }
    }
}
