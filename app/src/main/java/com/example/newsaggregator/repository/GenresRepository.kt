package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.data.Magazine
import com.example.newsaggregator.data.Manga
import com.example.newsaggregator.network.service.GenresService

interface GenresRepository {
    suspend fun getAnimeGenres(): List<Anime>
    suspend fun getMangaGenres(): List<Manga>
    suspend fun getMagazines(): List<Magazine>
}

class GenresRepositoryImpl(private val genresService: GenresService) : GenresRepository {
    override suspend fun getAnimeGenres(): List<Anime> =
        genresService.getAnimeGenres().data

    override suspend fun getMangaGenres(): List<Manga> =
        genresService.getMangaGenres().data

    override suspend fun getMagazines(): List<Magazine> =
        genresService.getMagazines().data.data
}