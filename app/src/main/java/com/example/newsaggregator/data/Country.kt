package com.example.newsaggregator.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Dictionary

@Serializable
data class Country(
    @SerialName("name")
    val name: String,
    @SerialName("topLevelDomain")
    val topLevelDomain: List<String>,
    @SerialName("alpha2Code")
    val alpha2Code: String,
    @SerialName("alpha3Code")
    val alpha3Code: String,
    @SerialName("callingCodes")
    val callingCodes: List<String>,
    @SerialName("capital")
    val capital: String,
    @SerialName("altSpellings")
    val altSpellings: List<String>,
    @SerialName("region")
    val region: String,
    @SerialName("subregion")
    val subregion: String,
    @SerialName("population")
    val population: Long,
    @SerialName("latlng")
    val latLng: Pair<Float, Float>,
    @SerialName("demonym")
    val demonym: String,
    @SerialName("area")
    val area: Float,
    @SerialName("gini")
    val gini: Float,
    @SerialName("timezones")
    val timezones: List<String>,
    @SerialName("borders")
    val borders: List<String>,
    @SerialName("nativeName")
    val nativeName: String,
    @SerialName("nativeCode")
    val nativeCode: String,
    @SerialName("currencies")
//    todo: Make an obj
    val currencies: List<String>,
//    todo: Make an obj
    @SerialName("languages")
    val languages: List<String>,
//    todo: Make an obj
    @SerialName("translations")
    val translations: Dictionary<String, String>,,
    @SerialName("flag")
    val flag: String,
//    todo: Make an obj
    @SerialName("regionalBlocs")
    val regionalBlocs: List<String>
)