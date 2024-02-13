package com.example.newsaggregator.ui.screen.animedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.repository.AnimeRepository
import com.example.newsaggregator.ui.navigation.ANIME_ID
import com.example.newsaggregator.utils.orInvalidId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val animeRepository: AnimeRepository,
) : ViewModel(
) {
    private val animeId : Int = savedStateHandle.get<Int>(ANIME_ID).orInvalidId()
    private val _viewState = MutableStateFlow(AnimeDetailsViewState())
    val viewState: StateFlow<AnimeDetailsViewState> = _viewState.asStateFlow()

    init {
        getAnimeDetails()
    }

    private fun getAnimeDetails() {
        viewModelScope.launch {
            runCatching {
                animeRepository.getAnimeById(animeId)
            }.onSuccess { anime ->
                _viewState.update { it.copy(anime = anime) }
            }.onFailure {
//                todo: handle error
                it.printStackTrace()
            }
        }
    }
}


data class AnimeDetailsViewState(
    val anime: Anime? = null
)