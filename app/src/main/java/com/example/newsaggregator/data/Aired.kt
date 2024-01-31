package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String?,
    @SerialName("to")
    val to: String?,
    @SerialName("prop")
    val prop: Prop?
)

@Serializable
data class Prop(
    @SerialName("from")
    val from: DateItem,
    @SerialName("to")
    val to: DateItem,
    @SerialName("string")
    val string: String = ""
)

@Serializable
data class DateItem(
    @SerialName("day")
    val date: Int?,
    @SerialName("month")
    val month: Int?,
    @SerialName("year")
    val year: Int?
)