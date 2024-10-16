package tosbik.ao.tmass.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    maxFontSize: Float = 100f, // Başlangıç font büyüklüğü
    minFontSize: Float = 12f,  // Minimum font büyüklüğü
    padding: Dp = Dp(16f),     // Kenar boşluğu
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal
) {
    var textSize by remember { mutableStateOf(maxFontSize) }

    Box(
        modifier = modifier.padding(padding)
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = fontWeight,
            style = TextStyle(
                fontSize = textSize.sp,
                fontFamily = FontFamily.Default
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxSize(),
            onTextLayout = { textLayoutResult ->
                // Eğer metin taşarsa font size'ı küçült
                if (textLayoutResult.didOverflowWidth) {
                    if (textSize > minFontSize) {
                        textSize *= 0.9f // Font size'ı %90'a düşür
                    }
                }
            }
        )
    }
}