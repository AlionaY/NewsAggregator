package com.example.newsaggregator.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.events
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.repository.AnimeRepository
import com.example.newsaggregator.ui.model.AppEvent
import com.example.newsaggregator.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 10

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    private val animeListFlow = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = {
            AnimePagingSource(animeRepository = animeRepository)
        }).flow.cachedIn(viewModelScope)

    private val _viewState = MutableStateFlow(HomeViewState(animeListFlow))
    val viewState: StateFlow<HomeViewState> = _viewState.asStateFlow()


    fun onAnimeItemClick(item: Anime) {
        viewModelScope.launch {
            events.emit(
                AppEvent.NavigateWithArgument(
                    destination = Routes.AnimeDetails,
                    argument = item.id.toString()
                )
            )
        }
    }
}
