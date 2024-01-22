package com.example.newsaggregator.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
): ViewModel() {

    fun getAllCountries() {
        viewModelScope.launch {
            val data = countriesRepository.getAnimeGenres()
            Log.d("####", "countries data $data")
        }
    }
}