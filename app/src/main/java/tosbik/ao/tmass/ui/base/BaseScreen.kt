package tosbik.ao.tmass.ui.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.ui.components.EmptyScreen
import tosbik.ao.tmass.ui.components.LoadingBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import tosbik.ao.tmass.R
import tosbik.ao.tmass.common.extensions.advancedShadow
import tosbik.ao.tmass.ui.calendar.CalendarScreen
import tosbik.ao.tmass.ui.home.HomeScreen
import tosbik.ao.tmass.ui.navigation.NavRoot
import tosbik.ao.tmass.ui.profile.ProfileScreen
import tosbik.ao.tmass.ui.projects.ProjectsScreen
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.DividerColor
import tosbik.ao.tmass.ui.theme.ErrorColor
import tosbik.ao.tmass.ui.theme.PrimaryColor
import tosbik.ao.tmass.ui.theme.SoftWhite
import tosbik.ao.tmass.ui.theme.TextColorPrimary
import tosbik.ao.tmass.ui.theme.TextColorSecondary

sealed class NavigationItem(var route: NavRoot, var icon: Int) {
    data object Home : NavigationItem(NavRoot.Home, R.drawable.house)
    data object Projects : NavigationItem(NavRoot.Projects, R.drawable.projects)
    data object Calendar : NavigationItem(NavRoot.Calendar, R.drawable.calendar)
    data object Profile : NavigationItem(NavRoot.Profile, R.drawable.profile)

    companion object {
        val all = listOf(Home, Projects, Calendar, Profile)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(navHostController: NavHostController = rememberNavController()) {
    val baseNavController = rememberNavController()
    val currentRoute by baseNavController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = SoftWhite,
                modifier = Modifier.advancedShadow(
                    color = DividerColor,
                    alpha = 0.2f,
                    offsetY = (-8).dp,
                    shadowBlurRadius = 12.dp
                ),
                content = {
                    NavigationItem.all.forEach { item ->
                        Box(
                            modifier = Modifier

                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        baseNavController.navigate(item.route.root)
                                    }
                                )
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(item.icon),
                                    contentDescription = item.route.root,
                                    modifier = Modifier.size(26.dp),
                                    colorFilter = if (currentRoute?.destination?.route == item.route.root) {
                                        ColorFilter.tint(PrimaryColor)
                                    } else {
                                        ColorFilter.tint(TextColorSecondary)
                                    }
                                )
                                Spacer(modifier = Modifier.size(2.dp))
                                Text(
                                    text = item.route.root,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = if (currentRoute?.destination?.route == item.route.root) {
                                        PrimaryColor
                                    } else {
                                        TextColorSecondary
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = baseNavController,
            startDestination = NavRoot.Home.root,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .background(BackgroundColor),
        ) {
            composable(NavRoot.Home.root) { HomeScreen(baseNavController) }
            composable(NavRoot.Projects.root) { ProjectsScreen(baseNavController) }
            composable(NavRoot.Calendar.root) { CalendarScreen() }
            composable(NavRoot.Profile.root) { ProfileScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    BaseScreen()
}