package tosbik.ao.tmass.ui.calendar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
import tosbik.ao.tmass.common.utils.CalendarUtils
import tosbik.ao.tmass.domain.model.HomeTaskModel
import tosbik.ao.tmass.ui.components.AppButton
import tosbik.ao.tmass.ui.components.TaskCardView
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.DividerColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.SoftWhite
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary
import tosbik.ao.tmass.ui.theme.WhiteColor

enum class TaskType(val value: String) {
    PRIORITY("Priority Tasks"),
    DAILY("Daily Tasks"),
}

@Composable
fun CalendarScreen() {
    val coroutineScope = rememberCoroutineScope()
    val statusBarInsets = WindowInsets.statusBars.asPaddingValues()
    val selectedDate = remember { mutableStateOf(CalendarUtils.getCurrentDate()) }
    val selectedTaskType = remember { mutableStateOf(TaskType.PRIORITY) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = statusBarInsets.calculateTopPadding() + 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "Calendar",
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    CalendarUtils.formatCurrentDate(),
                    style = MaterialTheme.typography.titleLarge,
                    color = TextColorPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                AppButton(
                    text = "Add Task", modifier = Modifier
                        .width(120.dp)
                        .height(32.dp)
                ) { }
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                items(CalendarUtils.getDaysInMonthAsLocalDate(selectedDate = CalendarUtils.getCurrentDate())) { date ->
                    val isSelected = date == selectedDate.value
                    val color by animateColorAsState(
                        targetValue = if (isSelected) WhiteColor else PrimaryColor,
                        animationSpec = tween(durationMillis = 200), label = ""
                    )
                    val backgroundColor by animateFloatAsState(
                        targetValue = if (isSelected) 1f else 0.1f,
                        animationSpec = tween(durationMillis = 200), label = ""
                    )
                    val size by animateFloatAsState(
                        targetValue = if (isSelected) 1.2f else 1f,
                        animationSpec = tween(durationMillis = 200), label = ""
                    )

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .scale(size)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { selectedDate.value = date }
                            .background(PrimaryColor.copy(alpha = backgroundColor)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = CalendarUtils.getDayName(date),
                                style = MaterialTheme.typography.bodySmall,
                                color = color,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = date.dayOfMonth.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = color,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TaskTabRow(
                tabs = listOf(TaskType.PRIORITY.value, TaskType.DAILY.value),
                selectedTab = selectedTaskType.value.ordinal
            ) {
                selectedTaskType.value = TaskType.entries.toTypedArray()[it]
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(contentPadding = PaddingValues(vertical = 12.dp)) {
                items(10) { i ->
                    TaskCardView(i)
                }
            }
        }
    }
}

@Composable
private fun TaskTabButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ).drawBehind {
                if (isSelected) {
                    drawLine(
                        color = PrimaryColor,
                        start = Offset(screenWidth * 0.5f, size.height),
                        end = Offset(size.width - screenWidth * 0.5f, size.height),
                        strokeWidth = 4f
                    )
                }
            }.padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = if (isSelected) PrimaryColor else TextColorPrimary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun TaskTabRow(tabs: List<String>, selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        tabs.forEachIndexed { index, tab ->
            TaskTabButton(
                text = tab,
                isSelected = index == selectedTab,
                modifier = Modifier.weight(1f),
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Preview
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}