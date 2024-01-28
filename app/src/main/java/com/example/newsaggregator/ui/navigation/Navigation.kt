package com.example.newsaggregator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsaggregator.ui.screen.home.HomeScreen
import com.example.newsaggregator.ui.screen.profile.ProfileScreen
import com.example.newsaggregator.ui.screen.search.SearchScreen
import com.example.newsaggregator.ui.screen.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
//        composable(Routes.Splash.route) {
//            SplashScreen(navController)
//        }

        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.Search.route) {
            SearchScreen(navController)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController)
        }
    }
}