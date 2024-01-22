package com.example.newsaggregator.ui

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
class MainViewModel @Inject constructor(
    private val randomQuotesRepository: RandomQuotesRepository
): ViewModel() {

    val randomQuotes = MutableStateFlow<List<Quote>>(emptyList())

    init {
        getAllRandomQuotes()

        viewModelScope.launch {
            val data = randomQuotesRepository.getRandomQuoteByCharacterName("naruto")
            Log.d("$$$", "data $data")
        }
    }

    private fun getAllRandomQuotes() {
        viewModelScope.launch {
            randomQuotes.value += randomQuotesRepository.get10RandomQuotes()
        }
    }
}