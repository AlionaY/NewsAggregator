package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.network.service.AnimeService

interface AnimeRepository {
    suspend fun getAnimeList(
        page: Int,
        limit: Int,
        query: String = ""
    ): List<Anime>

    suspend fun getAnimeGenres(): List<Genre>

}

class AnimeRepositoryImpl(private val service: AnimeService) : AnimeRepository {
    override suspend fun getAnimeList(page: Int, limit: Int, query: String): List<Anime> =
        service.getAnimeList(
            page = page,
            limit = limit,
            query = query
        ).data

    override suspend fun getAnimeGenres(): List<Genre> =
        service.getAnimeGenres().data
}