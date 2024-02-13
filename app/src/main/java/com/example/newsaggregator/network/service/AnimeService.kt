package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.PaginationResponse

private const val ANIME = "anime"
private const val ANIME_GENRES = "genres/anime"

private const val PAGE = "page"
private const val LIMIT = "limit"
private const val QUERY = "q"


interface AnimeService {
    suspend fun getAnimeList(
        page: Int,
        limit: Int,
        query: String
    ): PaginationResponse<Anime>

    suspend fun getAnimeById(id: Int): DataResponse<Anime>

    suspend fun getAnimeGenres(): DataResponse<List<Genre>>
}

class AnimeServiceImpl(private val apiClient: ApiClient) : AnimeService {
    override suspend fun getAnimeList(
        page: Int,
        limit: Int,
        query: String
    ): PaginationResponse<Anime> =
        apiClient.get(
            path = ANIME,
            params = mapOf(
                PAGE to page,
                LIMIT to limit,
                QUERY to query
            )
        )

    override suspend fun getAnimeById(id: Int): DataResponse<Anime> =
        apiClient.get("$ANIME/$id")

    override suspend fun getAnimeGenres(): DataResponse<List<Genre>> =
        apiClient.get(ANIME_GENRES)
}