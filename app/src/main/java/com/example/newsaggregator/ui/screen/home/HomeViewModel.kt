package com.example.newsaggregator.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 10
private const val PREFETCH_SIZE = 10

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

//    private val paginator = DefaultPaginator(
//        initialKey = _viewState.value.page,
//        onLoadUpdated = { isLoading ->
//            _viewState.update { it.copy(isLoading = isLoading) }
//        },
//        onRequest = { nextPage ->
//            animeRepository.getAnimeList(page = nextPage, limit = PAGE_SIZE)
//        },
//        getNextKey = { list ->
//            val nextPage = _viewState.value.page.plus(1)
//            nextPage
//        },
//        onError = { error ->
//            _viewState.update { it.copy(error = error?.message.orEmpty()) }
//        },
//        onSuccess = { items, newKey ->
//            Log.d("$$$", "items ${items.size}")
//            _viewState.update {
//                it.copy(
//                    list = _viewState.value.list + items,
//                    page = newKey,
//                    endReached = items.isEmpty()
//                )
//            }
//        }
//    )
//
//    init {
//        loadNextPage()
//    }
//
//
//    fun loadNextPage() {
//        viewModelScope.launch {
//            paginator.loadNextPage()
//        }
//    }


    fun onAnimeItemClick(item: Anime) {
//        todo: navigate to anime details
    }
}
