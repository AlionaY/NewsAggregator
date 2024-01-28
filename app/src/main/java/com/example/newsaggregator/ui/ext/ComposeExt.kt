package com.example.newsaggregator.ui.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import com.example.newsaggregator.ui.base.BottomNavBarItems

val LocalNavigationBarOptions = compositionLocalOf { NavBarOptions() }

class NavBarOptions {
    val selectedTab = mutableStateOf(BottomNavBarItems.Home)
}

@Composable
fun ProvideLocalNavBarItems(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalNavigationBarOptions provides NavBarOptions()) {
        content()
    }
}