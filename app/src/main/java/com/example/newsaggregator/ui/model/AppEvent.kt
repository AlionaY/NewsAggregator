package com.example.newsaggregator.ui.model

import androidx.navigation.NavOptionsBuilder
import com.example.newsaggregator.ui.navigation.Routes

sealed class AppEvent {
    data class Navigate(
        val destination: Routes,
        val builder: NavOptionsBuilder.() -> Unit = {}
    ) : AppEvent()

    data class NavigateWithArgument(
        val destination: Routes,
        val argument: String,
        val builder: NavOptionsBuilder.() -> Unit = {}
    ) : AppEvent()

    data class ErrorDialog(val exception: Exception) : AppEvent()

    object PopBackStack : AppEvent()
}