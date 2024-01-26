package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Quote
import com.example.newsaggregator.network.client.ApiClient

private const val RANDOM_QUOTE = "random"
private const val RANDOM_QUOTE_BY_ANIME_TITLE = "random/anime?title="
private const val RANDOM_QUOTE_BY_CHARACTER_NAME = "random/character?name="
private const val RANDOM_10_QUOTES = "quotes"
private const val RANDOM_10_QUOTES_BY_ANIME_TITLE = "quotes/anime?title="
private const val RANDOM_10_QUOTES_BY_CHARACTER_NAME = "quotes/character?name="

interface RandomQuotesService {
    suspend fun getRandomQuote(): Quote
    suspend fun getRandomQuoteByAnimeTitle(title: String): Quote
    suspend fun getRandomQuoteByCharacterName(name: String): Quote
    suspend fun get10RandomQuotes(): List<Quote>
    suspend fun get10RandomQuotesByAnimeTitle(title: String): List<Quote>
    suspend fun get10RandomQuotesByCharacterName(name: String): List<Quote>
}

class RandomQuotesServiceImpl(private val apiClient: ApiClient) : RandomQuotesService {

    override suspend fun getRandomQuote(): Quote =
        apiClient.get(RANDOM_QUOTE)

    override suspend fun getRandomQuoteByAnimeTitle(title: String): Quote =
        apiClient.get("$RANDOM_QUOTE_BY_ANIME_TITLE$title")

    override suspend fun getRandomQuoteByCharacterName(name: String): Quote =
        apiClient.get("$RANDOM_QUOTE_BY_CHARACTER_NAME$name")

    override suspend fun get10RandomQuotes(): List<Quote> =
        apiClient.get(RANDOM_10_QUOTES)

    override suspend fun get10RandomQuotesByAnimeTitle(title: String): List<Quote> =
        apiClient.get("$RANDOM_10_QUOTES_BY_ANIME_TITLE$title")

    override suspend fun get10RandomQuotesByCharacterName(name: String): List<Quote> =
        apiClient.get("$RANDOM_10_QUOTES_BY_CHARACTER_NAME$name")
}