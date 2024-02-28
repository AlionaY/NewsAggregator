@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsaggregator.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.events
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.ui.util.HandleAppEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    HandleAppEvents(appEventFlow = viewModel.events, navController = navController)

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
        onAnimeItemClick = { viewModel.onAnimeItemClick(it) })
}

@Composable
private fun ScreenContent(
    animeList: LazyPagingItems<Anime>,
    pullToRefreshState: PullToRefreshState,
    modifier: Modifier = Modifier,
    onAnimeItemClick: (Anime) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val scaleFraction = getScaleFraction(pullToRefreshState)
    val isFirstItemVisible by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }

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
                    lazyListState = lazyListState,
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

        AnimatedVisibility(
            visible = isFirstItemVisible,
            modifier = Modifier.align(Alignment.BottomEnd).wrapContentSize(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            GoToTopButton(
                lazyListState = lazyListState,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Composable
private fun GoToTopButton(
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    FloatingActionButton(
        onClick = { scrollToFistItem(scope, lazyListState) },
        modifier = modifier,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = null
        )
    }
}

private fun scrollToFistItem(
    scope: CoroutineScope,
    lazyListState: LazyListState
) {
    scope.launch {
        lazyListState.animateScrollToItem(0)
    }
}

@Composable
private fun getScaleFraction(pullToRefreshState: PullToRefreshState) =
    if (pullToRefreshState.isRefreshing) {
        1f
    } else {
        LinearOutSlowInEasing.transform(pullToRefreshState.progress).coerceIn(0f, 1f)
    }