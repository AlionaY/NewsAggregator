package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AnimeType(val alias: String) {
    @SerialName("TV")
    Tv("TV"),
    @SerialName("Movie")
    Movie("movie"),
    @SerialName("OVA")
    //    Original Video Animation
    Ova("ova"),
    @SerialName("Special")
    Special("special"),
    //    original net animation
    @SerialName("ONA")
    Ona("ona"),
    @SerialName("Music")
    Music("music"),
    @SerialName("CM")
    ClosedMouth("cm"),
    @SerialName("PV")
    PromotionalVideo("pv"),
    @SerialName("TV Special")
    TvSpecial("tv_special"),
}