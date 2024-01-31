package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Season(val alias: String) {
    @SerialName("winter")
    Winter("winter"),
    @SerialName("spring")
    Spring("spring"),
    @SerialName("summer")
    Summer("summer"),
    @SerialName("fall")
    Autumn("autumn")
}