package tosbik.ao.tmass.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
import tosbik.ao.tmass.domain.model.HomeTaskModel
import tosbik.ao.tmass.ui.theme.DividerColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.SoftWhite
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary
import tosbik.ao.tmass.ui.theme.WhiteColor

@Composable
fun TaskCardView(i: Int) {
    val task = HomeTaskModel()
    val isCompleted = i % 2 == 0
    val checkButtonModifier: Modifier = if(isCompleted) Modifier else Modifier
        .clip(CircleShape)
        .size(40.dp)
        .border(1.dp, SecondaryColor, CircleShape)
        .padding(3.dp)
    Box(
        modifier = Modifier
            .advancedShadow(
                color = DividerColor,
                alpha = 0.1f,
                offsetY = 8.dp,
                shadowBlurRadius = 12.dp
            )
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .clip(MaterialTheme.shapes.medium)
            .background(if (isCompleted) SoftWhite else WhiteColor)
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .alpha(if (isCompleted) 0.4f else 1f)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextColorPrimary,
                        maxLines = 2,
                    )
                    Text(
                        if (isCompleted) "Completed" else "In Progress...",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isCompleted) PrimaryColor else SecondaryColor,
                        maxLines = 2,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = checkButtonModifier,
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = if (isCompleted) PrimaryColor else SecondaryColor
                        ),
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(if (isCompleted) R.drawable.check_solid else R.drawable.work_in_progress),
                            contentDescription = "Arrow Right",
                            modifier = Modifier.size(16.dp),
                            tint = WhiteColor
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = DividerColor
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.calendar_regular),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    task.dueDate,
                    fontSize = 12.sp,
                    color = TextColorSecondary,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    task.priority,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(SecondaryColor.copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    color = SecondaryColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}