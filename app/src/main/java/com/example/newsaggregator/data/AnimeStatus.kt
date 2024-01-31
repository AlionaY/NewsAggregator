package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AnimeStatus() {
    @SerialName("Finished Airing")
    Complete,
    @SerialName("Currently Airing")
    Airing,
    @SerialName("Not yet aired")
    Upcoming
}