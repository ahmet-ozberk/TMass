package tosbik.ao.tmass.ui.projects

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
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

@Composable
fun ProjectsScreen(navController: NavController = rememberNavController()) {
    val statusBarInsets = WindowInsets.statusBars.asPaddingValues()

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
                    .advancedShadow(
                        color = DividerColor,
                        alpha = 0.2f,
                        offsetY = 8.dp,
                        shadowBlurRadius = 12.dp
                    )
                    .background(SoftWhite)
                    .padding(
                        top = statusBarInsets.calculateTopPadding() + 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        "Project management made easy",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Light,
                        color = TextColorSecondary
                    )
                    Text(
                        "My Projects",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColorPrimary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {}, colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = CardWhiteColor
                    ),
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.filter_solid),
                        contentDescription = "Filter",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                                    fontSize = 24.sp,
                                    //fontWeight = FontWeight.Black,
                                    modifier = Modifier.weight(1f),
                                    fontFamily = FontFamily(Font(R.font.mm)),
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
        ) {
            val randomValue = Random().nextFloat()
            Text(
                "In Progress (${(randomValue * 100).toInt()}%)",
                fontSize = 12.sp,
                color = statusColor,
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
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Text(
                title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = textColor,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                value.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
            )
        }
    }
}


@Preview
@Composable
fun ProjectsScreenPreview() {
    ProjectsScreen()
}