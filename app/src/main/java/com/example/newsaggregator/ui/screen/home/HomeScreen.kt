@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsaggregator.ui.screen.home

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.ui.navigation.Routes

//todo: add arrow to jump to top after 1st/2nd item isn`t visible
//todo: show more info in item card
//todo: add theme, colors, typography

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val (animeItemsList) = viewState

    val animeList = animeItemsList.collectAsLazyPagingItems()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(key1 = animeList.loadState) {
        if (animeList.loadState.refresh == LoadState.Loading) {
            pullToRefreshState.endRefresh()
        }
    }

    LaunchedEffect(key1 = pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) animeList.refresh()
    }

    ScreenContent(
        animeList = animeList,
        pullToRefreshState = pullToRefreshState,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
        onAnimeItemClick = {
//            viewModel.onAnimeItemClick(it)
            navController.navigate("${Routes.AnimeDetails.route}/${it.id}")
        })
}

@Composable
private fun ScreenContent(
    animeList: LazyPagingItems<Anime>,
    pullToRefreshState: PullToRefreshState,
    modifier: Modifier = Modifier,
    onAnimeItemClick: (Anime) -> Unit,
) {
    val scaleFraction = getScaleFraction(pullToRefreshState)

    Box(modifier = modifier) {
        when {
            animeList.loadState.refresh == LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                    strokeWidth = 4.dp
                )
            }

            else -> {
                AnimeList(
                    animeList = animeList,
                    modifier = Modifier.fillMaxSize(),
                    onAnimeItemClick = { onAnimeItemClick(it) }
                )
            }
        }

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction)
        )
    }
}

@Composable
private fun getScaleFraction(pullToRefreshState: PullToRefreshState) =
    if (pullToRefreshState.isRefreshing) {
        1f
    } else {
        LinearOutSlowInEasing.transform(pullToRefreshState.progress).coerceIn(0f, 1f)
    }