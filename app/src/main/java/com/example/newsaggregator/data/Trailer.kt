package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("youtube_id")
    val youtubeId: String,
    @SerialName("url")
    val url: String,
    @SerialName("embed_url")
    val embedUrl: String
)