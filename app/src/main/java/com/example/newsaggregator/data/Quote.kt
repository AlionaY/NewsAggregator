package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("anime")
    val anime: String,
    @SerialName("character")
    val animeName: String,
    @SerialName("quote")
    val quote: String
)