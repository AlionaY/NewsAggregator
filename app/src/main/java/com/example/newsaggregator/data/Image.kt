package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageItem(
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("small_image_url")
    val smallImageUrl: String,
    @SerialName("large_image_url")
    val largeImageUrl: String
)

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: ImageItem,
    @SerialName("webp")
    val webp: ImageItem
)