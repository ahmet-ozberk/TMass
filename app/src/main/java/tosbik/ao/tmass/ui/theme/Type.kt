package tosbik.ao.tmass.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import tosbik.ao.tmass.R


val interFontFamily = FontFamily(Font(R.font.inter))
val montserratFontFamily = FontFamily(
    listOf(
        Font(R.font.ml,FontWeight.Light),
        Font(R.font.mm,FontWeight.Medium),
        Font(R.font.mr,FontWeight.Medium),
        Font(R.font.msb,FontWeight.SemiBold),
        Font(R.font.mbold,FontWeight.Bold),
        Font(R.font.mblack,FontWeight.Black),
        Font(R.font.meb,FontWeight.ExtraBold),
        Font(R.font.mel,FontWeight.ExtraLight),
        Font(R.font.mt,FontWeight.Thin),
    )
)

val interTypography = Typography(
    bodyLarge = TextStyle(fontFamily = interFontFamily),
    displayLarge= TextStyle(fontFamily = interFontFamily),
    displayMedium= TextStyle(fontFamily = interFontFamily),
    displaySmall= TextStyle(fontFamily = interFontFamily),
    headlineLarge= TextStyle(fontFamily = interFontFamily),
    headlineMedium= TextStyle(fontFamily = interFontFamily),
    headlineSmall= TextStyle(fontFamily = interFontFamily),
    titleLarge= TextStyle(fontFamily = interFontFamily),
    titleMedium= TextStyle(fontFamily = interFontFamily),
    titleSmall= TextStyle(fontFamily = interFontFamily),
    bodyMedium= TextStyle(fontFamily = interFontFamily),
    bodySmall= TextStyle(fontFamily = interFontFamily),
    labelLarge= TextStyle(fontFamily = interFontFamily),
    labelMedium= TextStyle(fontFamily = interFontFamily),
    labelSmall= TextStyle(fontFamily = interFontFamily),
)


val montserratTypography = Typography(
    bodyLarge = TextStyle(fontFamily = montserratFontFamily),
    displayLarge= TextStyle(fontFamily = montserratFontFamily),
    displayMedium= TextStyle(fontFamily = montserratFontFamily),
    displaySmall= TextStyle(fontFamily = montserratFontFamily),
    headlineLarge= TextStyle(fontFamily = montserratFontFamily),
    headlineMedium= TextStyle(fontFamily = montserratFontFamily),
    headlineSmall= TextStyle(fontFamily = montserratFontFamily),
    titleLarge= TextStyle(fontFamily = montserratFontFamily),
    titleMedium= TextStyle(fontFamily = montserratFontFamily),
    titleSmall= TextStyle(fontFamily = montserratFontFamily),
    bodyMedium= TextStyle(fontFamily = montserratFontFamily),
    bodySmall= TextStyle(fontFamily = montserratFontFamily),
    labelLarge= TextStyle(fontFamily = montserratFontFamily),
    labelMedium= TextStyle(fontFamily = montserratFontFamily),
    labelSmall= TextStyle(fontFamily = montserratFontFamily),
)