package com.example.newsaggregator.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse<T>(
    @SerialName("pagination")
    val pagination: Pagination,
    @SerialName("data")
    val data: List<T>
)

@Serializable
data class Pagination(
    @SerialName("last_visible_page")
    val lastVisiblePage: Int,
    @SerialName("has_next_page")
    val hasNextPage: Boolean
)