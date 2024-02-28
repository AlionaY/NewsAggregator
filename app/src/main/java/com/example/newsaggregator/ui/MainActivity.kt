package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.events
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.ui.component.BottomNavigationBar
import com.example.newsaggregator.ui.model.BottomNavBarItems
import com.example.newsaggregator.ui.navigation.Navigation
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import com.example.newsaggregator.ui.util.HandleAppEvents
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlin.time.toDurationUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            NewsAggregatorTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        HandleAppEvents(appEventFlow = viewModel.events, navController = navController)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                val isBottomBarVisible =
                    currentRoute in (BottomNavBarItems.entries.map { it.route.route })
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically { it / 2 },
                    exit = slideOutVertically { it / 2 }
                ) {
                    BottomNavigationBar(
                        selectedItem = currentRoute.orEmpty(),
                        onItemSelected = { viewModel.navigate(it) }
                    )
                }
            }
        ) { innerPadding ->

            Surface(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController = navController)
            }
        }
    }
}