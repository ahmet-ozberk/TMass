package tosbik.ao.tmass.ui.newtask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tosbik.ao.tmass.ui.components.EmptyScreen
import tosbik.ao.tmass.ui.components.LoadingBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun NewTaskScreen(navHostController: NavHostController) {

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewTaskScreenPreview() {
    val navController = rememberNavController()
    NewTaskScreen(navHostController = navController)
}