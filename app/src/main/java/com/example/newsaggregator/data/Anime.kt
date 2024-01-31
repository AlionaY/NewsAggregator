package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    @SerialName("mal_id")
    val id: Int,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: Images,
    @SerialName("trailer")
    val trailer: Trailer,
    @SerialName("approved")
    val approved: Boolean,
    @SerialName("titles")
    val titles: List<AnimeTitle>,
    @SerialName("title")
    val title: String,
    @SerialName("title_english")
    val titleEnglish: String,
    @SerialName("title_japanese")
    val titleJapanese: String,
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerialName("type")
    val animeType: AnimeType,
    @SerialName("source")
    val source: String,
    @SerialName("episodes")
    val episodes: Int,
    @SerialName("status")
    val animeStatus: AnimeStatus,
    @SerialName("airing")
    val airing: Boolean,
    @SerialName("aired")
    val aired: Aired,
    @SerialName("duration")
    val duration: String,
    @SerialName("rating")
    val rating: AnimeRating,
    @SerialName("score")
    val score: Int,
    @SerialName("scored_by")
    val scoredBy: Int,
    @SerialName("rank")
    val rank: Int,
    @SerialName("popularity")
    val popularity: Int,
    @SerialName("members")
    val members: Int,
    @SerialName("favorites")
    val favorites: Int,
    @SerialName("synopsis")
    val synopsis: String,
    @SerialName("background")
    val background: String,
    @SerialName("season")
    val season: Season,
    @SerialName("year")
    val year: Int,
    @SerialName("broadcast")
    val broadcast: Broadcast,
    @SerialName("producers")
    val producers: List<GeneralAnimeItemDetails>,
    @SerialName("licensors")
    val licensors: List<GeneralAnimeItemDetails>,
    @SerialName("studios")
    val studios: List<GeneralAnimeItemDetails>,
    @SerialName("genres")
    val genres: List<GeneralAnimeItemDetails>,
    @SerialName("explicit_genres")
    val explicitGenres: List<GeneralAnimeItemDetails>,
    @SerialName("themes")
    val themes: List<GeneralAnimeItemDetails>,
    @SerialName("demographics")
    val demographics: List<GeneralAnimeItemDetails>
)