package com.example.newsaggregator.network

import kotlinx.serialization.Serializable

@Serializable
data class AppException(
    val status: Boolean,
    val message: String,
    val code: Int
)

class ServerException(val exception: AppException) : Throwable(exception.message)