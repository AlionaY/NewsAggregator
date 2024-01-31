package com.example.newsaggregator.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,

) : ViewModel() {

    init {
//        getAnimeGenres()
        getAnimeList()
    }

    fun getAnimeGenres() {
        viewModelScope.launch {
            val animeGenres = animeRepository.getAnimeGenres()
            Log.d("$$$", "anime $animeGenres")
        }
    }

    fun getAnimeList() {
        viewModelScope.launch {
            val animeList = animeRepository.getAnimeList()
            Log.d("$$$", "anime list $animeList")
        }
    }
}