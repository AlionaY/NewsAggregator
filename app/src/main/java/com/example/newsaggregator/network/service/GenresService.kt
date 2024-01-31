package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.data.Magazine
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.PaginationResponse

private const val ANIME_GENRES = "genres/anime"
private const val MANGA_GENRES = "genres/manga"
private const val MAGAZINES_GENRES = "magazines"

interface GenresService {
    suspend fun getAnimeGenres(): DataResponse<List<Genre>>
    suspend fun getMangaGenres(): DataResponse<List<Genre>>
    suspend fun getMagazines(): PaginationResponse<Magazine>
}

class GenresServiceImpl(private val apiClient: ApiClient) : GenresService {
    override suspend fun getAnimeGenres(): DataResponse<List<Genre>> =
        apiClient.get(ANIME_GENRES)

    override suspend fun getMangaGenres(): DataResponse<List<Genre>> =
        apiClient.get(MANGA_GENRES)

    override suspend fun getMagazines(): PaginationResponse<Magazine> =
        apiClient.get(MAGAZINES_GENRES)
}