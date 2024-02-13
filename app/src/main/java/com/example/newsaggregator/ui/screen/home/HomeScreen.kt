package com.example.newsaggregator.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

//todo: add arrow to jump to top after 1st/2nd item isn`t visible
//todo: navigate to screen with anime details
//todo: show more info in item card
//todo: add theme, colors, typography

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val (animeItemsList) = viewState

    val animeList = animeItemsList.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            animeList.loadState.refresh == LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    strokeWidth = 4.dp
                )
            }

            else -> {
                AnimeList(
                    animeList = animeList,
                    modifier = Modifier.fillMaxSize(),
                    onAnimeItemClick = { viewModel.onAnimeItemClick(it) }
                )
            }
        }
    }
}