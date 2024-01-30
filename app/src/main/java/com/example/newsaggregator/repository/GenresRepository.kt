package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Genre
import com.example.newsaggregator.data.Magazine
import com.example.newsaggregator.network.service.GenresService

interface GenresRepository {
    suspend fun getAnimeGenres(): List<Genre>
    suspend fun getMangaGenres(): List<Genre>
    suspend fun getMagazines(): List<Magazine>
}

class GenresRepositoryImpl(private val genresService: GenresService) : GenresRepository {
    override suspend fun getAnimeGenres(): List<Genre> =
        genresService.getAnimeGenres().data

    override suspend fun getMangaGenres(): List<Genre> =
        genresService.getMangaGenres().data

    override suspend fun getMagazines(): List<Magazine> =
        genresService.getMagazines().data.data
}