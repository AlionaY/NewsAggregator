package com.example.newsaggregator.ui.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsaggregator.ui.common.InDevelopment

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//    todo: make loading animation of some anime chars
        InDevelopment(modifier = Modifier.fillMaxSize())

    }
}