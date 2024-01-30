package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Magazine
import com.example.newsaggregator.data.Manga
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.PaginationResponse

private const val ANIME_GENRES = "genres/anime"
private const val MANGA_GENRES = "genres/manga"
private const val MAGAZINES_GENRES = "magazines"

interface GenresService {
    suspend fun getAnimeGenres(): DataResponse<List<Anime>>
    suspend fun getMangaGenres(): DataResponse<List<Manga>>
    suspend fun getMagazines(): PaginationResponse<List<Magazine>>
}

class GenresServiceImpl(private val apiClient: ApiClient) : GenresService {
    override suspend fun getAnimeGenres(): DataResponse<List<Anime>> =
        apiClient.get(ANIME_GENRES)

    override suspend fun getMangaGenres(): DataResponse<List<Manga>> =
        apiClient.get(MANGA_GENRES)

    override suspend fun getMagazines(): PaginationResponse<List<Magazine>> =
        apiClient.get(MAGAZINES_GENRES)
}