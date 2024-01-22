package com.example.newsaggregator.ui.navigation

sealed class Routes(val route: String) {
    object Splash: Routes("splash")
//    screen with news
    object Home: Routes("home")
//    find quotes, video etc
    object Find: Routes("find")
//    account, settings,  vybrane etc
    object Account: Routes("account")
}