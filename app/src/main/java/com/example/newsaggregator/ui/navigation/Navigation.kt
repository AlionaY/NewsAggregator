package com.example.newsaggregator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.screen.animedetails.AnimeDetailsScreen
import com.example.newsaggregator.ui.screen.home.HomeScreen
import com.example.newsaggregator.ui.screen.profile.ProfileScreen
import com.example.newsaggregator.ui.screen.search.SearchScreen

const val ANIME_ID = "animeId"

private val animeIdNavArg = navArgument(ANIME_ID) { type = NavType.IntType }

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.Search.route) {
            SearchScreen(navController)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController)
        }
        composable(
            route = Routes.AnimeDetails.toRoute(),
            arguments = listOf(animeIdNavArg)
        ) {
            AnimeDetailsScreen(navController)
        }
    }
}

fun Routes.AnimeDetails.toRoute() = "$route/{$ANIME_ID}"