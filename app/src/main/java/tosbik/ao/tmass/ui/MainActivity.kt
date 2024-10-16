package tosbik.ao.tmass.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import tosbik.ao.tmass.ui.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import tosbik.ao.tmass.ui.navigation.NavRoot
import tosbik.ao.tmass.ui.theme.BackgroundColor
import tosbik.ao.tmass.ui.theme.TMassTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMassTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BackgroundColor) {
                    val navController = rememberNavController()
                    val startDestination = NavRoot.Base
                    NavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}