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
    bodyLarge = Typography().bodyLarge.copy(fontFamily = montserratFontFamily),
    displayLarge= Typography().displayLarge.copy(fontFamily = montserratFontFamily),
    displayMedium= Typography().displayMedium.copy(fontFamily = montserratFontFamily),
    displaySmall= Typography().displaySmall.copy(fontFamily = montserratFontFamily),
    headlineLarge= Typography().headlineLarge.copy(fontFamily = montserratFontFamily),
    headlineMedium= Typography().headlineMedium.copy(fontFamily = montserratFontFamily),
    headlineSmall= Typography().headlineSmall.copy(fontFamily = montserratFontFamily),
    titleLarge= Typography().titleLarge.copy(fontFamily = montserratFontFamily),
    titleMedium= Typography().titleMedium.copy(fontFamily = montserratFontFamily),
    titleSmall= Typography().titleSmall.copy(fontFamily = montserratFontFamily),
    bodyMedium= Typography().bodyMedium.copy(fontFamily = montserratFontFamily),
    bodySmall= Typography().bodySmall.copy(fontFamily = montserratFontFamily),
    labelLarge= Typography().labelLarge.copy(fontFamily = montserratFontFamily),
    labelMedium= Typography().labelMedium.copy(fontFamily = montserratFontFamily),
    labelSmall= Typography().labelSmall.copy(fontFamily = montserratFontFamily),
).copy(

)