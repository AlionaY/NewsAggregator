package com.example.newsaggregator.ui.navigation

sealed class Routes(val route: String) {
//    screen with news
    object Home: Routes("home")
//    find quotes, video etc
    object Search: Routes("search")
//    account, settings,  vybrane etc
    object Profile: Routes("account")
    object AnimeDetails: Routes("anime_details")
}