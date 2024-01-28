package com.example.newsaggregator.ui.navigation

sealed class Routes(val route: String) {
    object Splash: Routes("splash")
//    screen with news
    object Home: Routes("home")
//    find quotes, video etc
    object Search: Routes("search")
//    account, settings,  vybrane etc
    object Profile: Routes("account")
}