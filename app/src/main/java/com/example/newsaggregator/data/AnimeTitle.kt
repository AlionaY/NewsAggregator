package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTitle(
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String
)