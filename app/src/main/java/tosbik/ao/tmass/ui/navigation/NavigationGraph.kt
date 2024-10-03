package tosbik.ao.tmass.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tosbik.ao.tmass.ui.auth.AuthScreen
import tosbik.ao.tmass.ui.auth.AuthViewModel
import tosbik.ao.tmass.ui.base.BaseScreen
import tosbik.ao.tmass.ui.base.BaseViewModel
import tosbik.ao.tmass.ui.newtask.NewTaskScreen
import tosbik.ao.tmass.ui.newtask.NewTaskViewModel
import tosbik.ao.tmass.ui.onboard.OnboardScreen
import tosbik.ao.tmass.ui.taskdetail.TaskDetailScreen
import tosbik.ao.tmass.ui.taskdetail.TaskDetailViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: NavRoot,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = Modifier.then(modifier),
        navController = navController,
        startDestination = startDestination.root,
    ) {
        composable(NavRoot.Onboard.root,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { rightToLeftBack() },
            popExitTransition = { leftToRightBack() }) {
            OnboardScreen(navController)
        }
        composable(NavRoot.Auth.root,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { rightToLeftBack() },
            popExitTransition = { leftToRightBack() }) {
            AuthScreen(navController)
        }
        composable(NavRoot.Base.root,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { rightToLeftBack() },
            popExitTransition = { leftToRightBack() }) {
            BaseScreen(navController)
        }
        composable(NavRoot.NewTask.root,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { rightToLeftBack() },
            popExitTransition = { leftToRightBack() }) {
            NewTaskScreen(navController)
        }
        composable(NavRoot.TaskDetail.root,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { rightToLeftBack() },
            popExitTransition = { leftToRightBack() }) {
            TaskDetailScreen(navController)
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() = slideIntoContainer(
    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
) + fadeIn(animationSpec = tween(300))

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = slideOutOfContainer(
    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
) + fadeOut(animationSpec = tween(300))

fun AnimatedContentTransitionScope<NavBackStackEntry>.rightToLeftBack() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
    ) + fadeIn(animationSpec = tween(300))

fun AnimatedContentTransitionScope<NavBackStackEntry>.leftToRightBack() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(300))