package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.network.service.AnimeService

interface AnimeRepository {
    suspend fun getAnimeList(): List<Anime>
    suspend fun getAnimeGenres(): List<Genre>

}

class AnimeRepositoryImpl(private val service: AnimeService) : AnimeRepository {
    override suspend fun getAnimeList(): List<Anime> =
        service.getAnimeList().data

    override suspend fun getAnimeGenres(): List<Genre> =
        service.getAnimeGenres().data
}