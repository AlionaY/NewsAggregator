package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Country
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val ALL_COUNTRIES = "all"
private const val GERMANY = "name/deutschland"

private const val POST = "posts"

private const val RANDOM_QUOTE = "random"

private const val ANIME_GENRES = "genres/anime"

interface CountriesService {
    suspend fun getAllCountries(): List<Country>
    suspend fun getPosts(): List<Post>

    suspend fun getAnimeQuote(): AnimeQuote

    suspend fun getAnimeGenres(): DataResponse<List<AnimeGenre>>
}

class CountriesServiceImpl(private val apiClient: ApiClient) : CountriesService {
    override suspend fun getAllCountries(): List<Country> =
        apiClient.get<List<Country>>(ALL_COUNTRIES)

    override suspend fun getPosts(): List<Post> =
        apiClient.get<List<Post>>(POST)

    override suspend fun getAnimeQuote(): AnimeQuote =
        apiClient.get(RANDOM_QUOTE)

    override suspend fun getAnimeGenres(): DataResponse<List<AnimeGenre>> =
        apiClient.get(ANIME_GENRES)

}

@Serializable
data class Post(
    @SerialName("userId")
    val userId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String
)

@Serializable
data class AnimeQuote(
    @SerialName("anime")
    val anime: String,
    @SerialName("character")
    val animeName: String,
    @SerialName("quote")
    val quote: String
)

@Serializable
data class AnimeGenre(
    @SerialName("mal_id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("count")
    val count: Int
)