package com.example.newsaggregator.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsaggregator.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    val animeListFlow = Pager(
        config = PagingConfig(
            10,
            enablePlaceholders = true,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            AnimePagingSource(animeRepository = animeRepository)
        }).flow

}