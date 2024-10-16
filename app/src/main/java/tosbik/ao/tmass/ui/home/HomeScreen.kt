package tosbik.ao.tmass.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
import tosbik.ao.tmass.common.utils.CalendarUtils
import tosbik.ao.tmass.domain.model.HomeTaskModel
import tosbik.ao.tmass.ui.components.TaskCardView
import tosbik.ao.tmass.ui.theme.AccentColor
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.CardWhiteColor
import tosbik.ao.tmass.ui.theme.DividerColor
import tosbik.ao.tmass.ui.theme.LightGray
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SecondaryColor
import tosbik.ao.tmass.ui.theme.SoftWhite
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary
import tosbik.ao.tmass.ui.theme.WhiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val projectsBottomSheet = remember { mutableStateOf(false) }

    val isCollapsed by remember {
        derivedStateOf {
            scrollBehavior.state.collapsedFraction > 0f
        }
    }


    if (projectsBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { projectsBottomSheet.value = false },
            containerColor = BackgroundColor,
            shape = RoundedCornerShape(4.dp),
        ) {
            HomeProjectsModalSheet {
                projectsBottomSheet.value = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column {
            MediumTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(
                                "${CalendarUtils.greetingBasedOnTime()} Ahmet!",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isCollapsed) LightGray else TextColorSecondary
                            )
                            Text(
                                CalendarUtils.formatCurrentDate(),
                                style = MaterialTheme.typography.titleLarge,
                                color = if (isCollapsed) SoftWhite else TextColorPrimary
                            )
                        }
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(CardWhiteColor)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.fake_user),
                            contentDescription = "Profile",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}, colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = CardWhiteColor
                        ),
                        modifier = Modifier
                            .padding(end = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bell_solid),
                            contentDescription = "Notification",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                },
                modifier = Modifier
                    .advancedShadow(
                        color = DividerColor,
                        alpha = 0.2f,
                        offsetY = 8.dp,
                        shadowBlurRadius = 12.dp
                    )
                    .background(SoftWhite),
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors().copy(
                    containerColor = BackgroundColor,
                    scrolledContainerColor = PrimaryColor
                )
            )
            LazyColumn(
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .fillMaxWidth()
            ) {
                item {
                    Column {
                        Spacer(modifier = Modifier.size(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                stringResource(R.string.summary),
                                style = MaterialTheme.typography.titleLarge,
                                color = TextColorPrimary
                            )
                            TextButton(
                                onClick = { projectsBottomSheet.value = true },
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = CardWhiteColor,
                                ),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxHeight(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "TMass Project",
                                        fontSize = 14.sp,
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Icon(
                                        painter = painterResource(R.drawable.down_arrow),
                                        contentDescription = "Arrow Right",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .advancedShadow(
                                        color = DividerColor,
                                        alpha = 0.1f,
                                        offsetY = 8.dp,
                                        shadowBlurRadius = 12.dp
                                    )
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(CardWhiteColor)
                                    .padding(16.dp)
                                    .weight(1f)
                            ) {
                                Column {
                                    Text(
                                        stringResource(R.string.assigned_task),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = TextColorSecondary
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(
                                        "21",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = TextColorPrimary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Box(
                                modifier = Modifier
                                    .advancedShadow(
                                        color = DividerColor,
                                        alpha = 0.1f,
                                        offsetY = 8.dp,
                                        shadowBlurRadius = 12.dp
                                    )
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(CardWhiteColor)
                                    .padding(16.dp)
                                    .weight(1f)
                            ) {
                                Column {
                                    Text(
                                        stringResource(R.string.completed_task),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = TextColorSecondary
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(
                                        "31",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = TextColorPrimary
                                    )
                                }
                            }
                        }
                        // AssignedTasksView()
                        Spacer(modifier = Modifier.size(32.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                ),
                        ) {
                            Column {
                                Text(
                                    stringResource(R.string.today_pending_task),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = TextColorPrimary,
                                )
                                Text(
                                    "18 ${stringResource(R.string.pending_task)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextColorSecondary,
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            TextButton(
                                onClick = {},
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = CardWhiteColor,
                                ),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text("New Task", fontSize = 14.sp)
                                    Icon(
                                        painter = painterResource(R.drawable.plus),
                                        contentDescription = "Arrow Right",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
                items(5) { i ->
                    TaskCardView(i)
                }
            }
        }
    }
}

@Composable
private fun AssignedTasksView() {
    Spacer(modifier = Modifier.size(32.dp))
    Text(
        stringResource(R.string.assigned_task),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextColorPrimary,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
        ),
    )
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(10) {
            val task = HomeTaskModel()
            Box {
                Box(
                    modifier = Modifier
                        .advancedShadow(
                            color = DividerColor,
                            alpha = 0.1f,
                            offsetY = 8.dp,
                            shadowBlurRadius = 12.dp
                        )
                        .width(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(WhiteColor)
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                task.category, modifier = Modifier
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(PrimaryColor.copy(alpha = 0.1f))
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                color = PrimaryColor,
                                textAlign = TextAlign.Center
                            )
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
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            task.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.size(24.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.calendar_regular),
                                contentDescription = "Email",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                task.dueDate,
                                fontSize = 12.sp,
                                color = TextColorSecondary,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                Text(
                    task.project,
                    fontSize = 12.sp,
                    color = WhiteColor,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset((-4).dp, 12.dp)
                        .shadow(
                            4.dp,
                            clip = true,
                            shape = MaterialTheme.shapes.medium,
                            spotColor = Color.Black.copy(alpha = 0.9f)
                        )
                        .clip(MaterialTheme.shapes.medium)
                        .background(PrimaryColor)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}