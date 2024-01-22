package com.example.newsaggregator.repository

import android.util.Log
import com.example.newsaggregator.data.Country
import com.example.newsaggregator.network.service.AnimeGenre
import com.example.newsaggregator.network.service.AnimeQuote
import com.example.newsaggregator.network.service.CountriesService
import com.example.newsaggregator.network.service.Post
import javax.inject.Inject

interface CountriesRepository {
    suspend fun getAllCountries() : List<Country>

    suspend fun getPosts(): List<Post>

    suspend fun getAnimeRandomQuote(): AnimeQuote

    suspend fun getAnimeGenres(): List<AnimeGenre>
}

class CountriesRepositoryImpl @Inject constructor(
    private val countriesService: CountriesService
): CountriesRepository {
    override suspend fun getAllCountries(): List<Country> = countriesService.getAllCountries()
    override suspend fun getPosts(): List<Post> {
        val data = countriesService.getPosts()
        Log.d("$$$", "repo response $data")
        return data
    }

    override suspend fun getAnimeRandomQuote(): AnimeQuote =
        countriesService.getAnimeQuote()

    override suspend fun getAnimeGenres(): List<AnimeGenre> =
        countriesService.getAnimeGenres().data
}