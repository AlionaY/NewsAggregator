package com.example.newsaggregator.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("status")
    val status: Boolean,
    @SerialName("message")
    val message: String
)
