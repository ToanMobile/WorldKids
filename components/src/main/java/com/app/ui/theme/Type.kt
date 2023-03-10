package com.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.ui.R

private val SFProFontFontFamily = FontFamily(
    Font(resId = R.font.poppins_regular, weight = FontWeight.Normal),
    Font(resId = R.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold)
)

val chatTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    displayMedium = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    displaySmall = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 21.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 21.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 21.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 21.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SFProFontFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp
    )
)
class TypographyApp(
    val text10Regular: TextStyle,
    val text10Bold: TextStyle,
    val text12Regular: TextStyle,
    val text12Bold: TextStyle,
    val text14Regular: TextStyle,
    val text14SemiBold: TextStyle,
    val text14Bold: TextStyle,
    val text20Regular: TextStyle,
    val text20Bold: TextStyle,
    val text24Regular: TextStyle,
    val text24Bold: TextStyle,
    val text26Regular: TextStyle,
    val text26Bold: TextStyle,
    val text36Bold: TextStyle,
    val text48Bold: TextStyle
)

val textNormalDimension = TypographyApp(
    text10Regular = chatTypography.labelSmall,
    text10Bold = chatTypography.displaySmall,
    text12Regular = chatTypography.labelMedium,
    text12Bold = chatTypography.displayMedium,
    text14Regular = chatTypography.bodySmall,
    text14SemiBold = chatTypography.titleSmall,
    text14Bold = chatTypography.displayLarge,
    text20Regular = chatTypography.bodySmall.copy(fontSize = 20.sp),
    text20Bold = chatTypography.displayLarge.copy(fontSize = 20.sp),
    text24Regular = chatTypography.bodyMedium.copy(fontSize = 24.sp),
    text24Bold = chatTypography.bodyMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    text26Regular = chatTypography.bodyMedium.copy(fontSize = 26.sp),
    text26Bold = chatTypography.bodyMedium.copy(fontSize = 26.sp, fontWeight = FontWeight.Bold),
    text36Bold = chatTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
    text48Bold = chatTypography.bodyLarge.copy(fontSize = 48.sp, fontWeight = FontWeight.Bold),
)

val text2100Dimension = TypographyApp(
    text10Regular = chatTypography.labelSmall.copy(fontSize = 23.sp),
    text10Bold = chatTypography.displaySmall.copy(fontSize = 23.sp),
    text12Regular = chatTypography.labelMedium.copy(fontSize = 27.6.sp),
    text12Bold = chatTypography.displayMedium.copy(fontSize = 27.6.sp),
    text14Regular = chatTypography.bodySmall.copy(fontSize = 32.2.sp),
    text14SemiBold = chatTypography.titleSmall.copy(fontSize = 32.2.sp),
    text14Bold = chatTypography.displayLarge.copy(fontSize = 32.2.sp),
    text20Regular = chatTypography.bodySmall.copy(fontSize = 46.sp),
    text20Bold = chatTypography.displayLarge.copy(fontSize = 46.sp),
    text24Regular = chatTypography.bodyMedium.copy(fontSize = 55.sp),
    text24Bold = chatTypography.bodyMedium.copy(fontSize = 55.sp, fontWeight = FontWeight.Bold),
    text26Regular = chatTypography.bodyMedium.copy(fontSize = 60.sp),
    text26Bold = chatTypography.bodyMedium.copy(fontSize = 60.sp, fontWeight = FontWeight.Bold),
    text36Bold = chatTypography.bodyLarge.copy(fontSize = 83.sp, fontWeight = FontWeight.Bold),
    text48Bold = chatTypography.bodyLarge.copy(fontSize = 110.sp, fontWeight = FontWeight.Bold),
)