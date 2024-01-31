package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AnimeRating(val alias: String) {
    @SerialName("G - All Ages")
    AllAges("g"),
    @SerialName("PG - Children")
    Children("pg"),
    @SerialName("PG-13 - Teens 13 or older")
    Teens13OrOlder("pg13"),
    @SerialName("R - 17+ (violence & profanity)")
    SeventeenPlus("r17"),
    @SerialName("R+ - Mild Nudity")
    MildNudity("r"),
    @SerialName("Rx - Hentai")
    Hentai("rx")
}