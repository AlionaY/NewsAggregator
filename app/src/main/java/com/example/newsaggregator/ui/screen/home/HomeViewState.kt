package com.example.newsaggregator.ui.screen.home

import androidx.paging.PagingData
import com.example.newsaggregator.data.Anime
import kotlinx.coroutines.flow.Flow

data class HomeViewState(
    val animeLiatFlow: Flow<PagingData<Anime>>,
    val isLoading: Boolean = false,
    val list: List<Anime> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1,
)