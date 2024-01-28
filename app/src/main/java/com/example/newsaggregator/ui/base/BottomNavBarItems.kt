package com.example.newsaggregator.ui.base

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsaggregator.R
import com.example.newsaggregator.ui.navigation.Routes

enum class BottomNavBarItems(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val title: Int,
    val route: Routes
) {
    Home(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        title = R.string.home,
        route = Routes.Home
    ),

    Search(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        title = R.string.search,
        route = Routes.Search
    ),

    Profile(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        title = R.string.profile,
        route = Routes.Profile
    )
}