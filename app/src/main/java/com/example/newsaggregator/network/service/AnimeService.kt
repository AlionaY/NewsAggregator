package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.PaginationResponse

private const val ANIME = "anime"
private const val ANIME_GENRES = "genres/anime"

interface AnimeService {
    suspend fun getAnimeList(): PaginationResponse<Anime>
    suspend fun getAnimeGenres(): DataResponse<List<Genre>>
}

class AnimeServiceImpl(private val apiClient: ApiClient) : AnimeService {
    override suspend fun getAnimeList(): PaginationResponse<Anime> =
        apiClient.get(ANIME)

    override suspend fun getAnimeGenres(): DataResponse<List<Genre>> =
        apiClient.get(ANIME_GENRES)
}