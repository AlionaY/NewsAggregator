package com.example.newsaggregator.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.newsaggregator.ui.component.ErrorDialog
import com.example.newsaggregator.ui.ext.navigate
import com.example.newsaggregator.ui.model.AppEvent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HandleAppEvents(
    appEventFlow: SharedFlow<AppEvent>,
    navController: NavController,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) {
    var exception by remember { mutableStateOf<String?>(null) }

    exception?.let {
        ErrorDialog(text = exception.orEmpty()) {
            exception = null
        }
    }

    LaunchedEffect(key1 = Unit) {
        appEventFlow.collect { event ->
            when (event) {
                is AppEvent.Navigate -> navController.navigate(
                    destination = event.destination,
                    builder = builder ?: event.builder
                )

                is AppEvent.NavigateWithArgument -> navController.navigate(
                    destination = event.destination,
                    argument = event.argument,
                    builder = builder ?: event.builder
                )

                is AppEvent.ErrorDialog -> exception = event.exception.message.orEmpty()
                AppEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }
}