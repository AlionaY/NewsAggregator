package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Language(
    @SerialName("iso639_1")
    val shortName1: String,
    @SerialName("iso639_2")
    val shortName2: String,
    @SerialName("name")
    val name: String,
    @SerialName("nativeName")
    val nativeName: String
)