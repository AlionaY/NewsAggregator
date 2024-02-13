package com.example.newsaggregator.ui.screen.home

import androidx.paging.PagingData
import com.example.newsaggregator.data.Anime
import kotlinx.coroutines.flow.Flow

data class HomeViewState(
    val animeListFlow: Flow<PagingData<Anime>>,
)