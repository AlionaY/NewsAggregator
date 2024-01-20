package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translations(
    @SerialName("de")
    val german: String,
    @SerialName("es")
    val spanish: String,
    @SerialName("fr")
    val french: String,
    @SerialName("ja")
    val japan: String,
    @SerialName("it")
    val italian: String,
    @SerialName("br")
    val breton: String,
    @SerialName("pt")
    val portuguese: String
)