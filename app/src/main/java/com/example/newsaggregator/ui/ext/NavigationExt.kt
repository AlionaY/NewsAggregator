package com.example.newsaggregator.ui.ext

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.newsaggregator.ui.navigation.Routes


fun NavController.navigate(
    destination: Routes,
    argument: String? = null,
    builder: NavOptionsBuilder.() -> Unit = {},
    onError: ((Throwable) -> Unit)? = null
) {
    runCatching {
        val arg = argument?.let { "/$argument" }.orEmpty()
        val route = "${destination.route}$arg"
        navigate(route, builder)
    }.onFailure { error ->
        onError?.let { it(error) }
    }
}