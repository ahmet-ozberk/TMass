package tosbik.ao.tmass.ui.projects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
import tosbik.ao.tmass.ui.components.AppTextField
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
import java.util.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(navController: NavController = rememberNavController()) {
    val statusBarInsets = WindowInsets.statusBars.asPaddingValues()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val searchState = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column {
            TopAppBar(title = {
                if (isSearching.value) {
                    AnimatedVisibility(
                        visible = isSearching.value,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut()
                    ) {
                        AppTextField(
                            value = searchState,
                            onValueChange = { searchState.value = it },
                            hintText = "Search",
                            modifier = Modifier
                                .padding(end = 16.dp),
                            leading = {
                                Icon(
                                    painter = painterResource(R.drawable.magnifying_glass_solid),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(16.dp),
                                    tint = SoftWhite
                                )
                            }
                        )
                    }
                } else {
                    Text(
                        "Projects",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColorPrimary
                    )
                }
            }, actions = {
                IconButton(
                    onClick = {
                        isSearching.value = !isSearching.value
                        searchState.value = ""
                    }, colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = CardWhiteColor
                    ),
                    modifier = Modifier
                        .padding(end = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(if (isSearching.value) R.drawable.magnifying_glass_minus_solid else R.drawable.magnifying_glass_solid),
                        contentDescription = "Search",
                        modifier = Modifier.size(16.dp)
                    )
                }
                IconButton(
                    onClick = {}, colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = CardWhiteColor
                    ),
                    modifier = Modifier
                        .padding(end = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.filter_solid),
                        contentDescription = "Filter",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }, modifier = Modifier
                .advancedShadow(
                    color = DividerColor,
                    alpha = 0.2f,
                    offsetY = 8.dp,
                    shadowBlurRadius = 12.dp
                )
                .background(SoftWhite),
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = BackgroundColor,
                    scrolledContainerColor = SoftWhite
                )
            )
            LazyColumn(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 86.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(10) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.elevatedCardColors(containerColor = CardWhiteColor),
                        modifier = Modifier
                            .fillMaxSize()
                            .advancedShadow(
                                color = DividerColor,
                                alpha = 0.1f,
                                offsetY = 8.dp,
                                shadowBlurRadius = 12.dp
                            )
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "Project Management",
                                    fontSize = 20.sp,
                                    modifier = Modifier.weight(1f),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
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
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet.",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light,
                                color = TextColorSecondary,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Justify
                            )
                            Spacer(modifier = Modifier.padding(vertical = 8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                ProjectProgress(
                                    statusColor = SecondaryColor,
                                    modifier = Modifier.weight(1f)
                                )
                                ProjectStatusTaskCard(
                                    title = "In Progress",
                                    value = Random().nextInt(100),
                                    color = SecondaryColor.copy(alpha = 0.06f),
                                    textColor = SecondaryColor
                                )
                                ProjectStatusTaskCard(
                                    title = "Pending",
                                    value = Random().nextInt(100),
                                    color = PrimaryColor.copy(alpha = 0.06f),
                                    textColor = PrimaryColor
                                )
                            }
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {},
            containerColor = PrimaryColor,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Add",
                    tint = WhiteColor,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    "New Project",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = WhiteColor
                )
            }
        }
    }
}

@Composable
private fun ProjectProgress(statusColor: Color = PrimaryColor, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(statusColor.copy(alpha = 0.06f))
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val randomValue = Random().nextFloat()
            Text(
                "In Progress (${(randomValue * 100).toInt()}%)",
                color = statusColor,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { randomValue },
                color = statusColor,
                trackColor = statusColor.copy(alpha = 0.2f),
                modifier = Modifier.fillMaxWidth(),
                gapSize = 8.dp,
            )
        }
    }
}


@Composable
private fun ProjectStatusTaskCard(
    title: String,
    value: Int,
    color: Color,
    textColor: Color
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(containerColor = color),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodySmall,
                color = textColor,
            )
            Text(
                value.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = textColor,)
        }
    }
}


@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreen()
}