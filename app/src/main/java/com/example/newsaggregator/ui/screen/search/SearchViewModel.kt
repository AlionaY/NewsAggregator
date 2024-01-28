package com.example.newsaggregator.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.Quote
import com.example.newsaggregator.repository.RandomQuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val randomQuotesRepository: RandomQuotesRepository
) : ViewModel() {

    val randomQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val loadingState = MutableStateFlow<LoadingState>(LoadingState.Idle)


    fun getAllRandomQuotes() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            runCatching {
                randomQuotes.value += randomQuotesRepository.get10RandomQuotes()
                loadingState.value = LoadingState.Idle
            }.onFailure {
                Log.d("$$$", "onFailure $it")
                loadingState.value = LoadingState.Error
            }.onSuccess {
                Log.d("$$$", "onSuccess $it")
                loadingState.value = LoadingState.Idle
            }
        }
    }
}

enum class LoadingState {
    Idle, Loading, Error
}