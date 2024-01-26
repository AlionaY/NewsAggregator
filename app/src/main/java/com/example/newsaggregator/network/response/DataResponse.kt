package com.example.newsaggregator.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
//    @SerialName("success")
//    val success: Boolean,
//    @SerialName("message")
//    val message: String,
    @SerialName("data")
    val data: T
)