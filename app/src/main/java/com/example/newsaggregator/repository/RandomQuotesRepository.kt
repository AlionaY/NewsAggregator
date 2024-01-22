package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Quote
import com.example.newsaggregator.network.service.RandomQuotesService
import javax.inject.Inject

interface RandomQuotesRepository {
    suspend fun getRandomQuote(): Quote
    suspend fun getRandomQuoteByAnimeTitle(title: String): Quote
    suspend fun getRandomQuoteByCharacterName(name: String): Quote
    suspend fun get10RandomQuotes(): List<Quote>
    suspend fun get10RandomQuotesByAnimeTitle(title: String): List<Quote>
    suspend fun get10RandomQuotesByCharacterName(name: String): List<Quote>

}

class RandomQuotesRepositoryImpl @Inject constructor(
    private val randomQuotesService: RandomQuotesService
) : RandomQuotesRepository {
    override suspend fun getRandomQuote(): Quote =
        randomQuotesService.getRandomQuote()

    override suspend fun getRandomQuoteByAnimeTitle(title: String): Quote =
        randomQuotesService.getRandomQuoteByAnimeTitle(title)

    override suspend fun getRandomQuoteByCharacterName(name: String): Quote =
        randomQuotesService.getRandomQuoteByCharacterName(name)

    override suspend fun get10RandomQuotes(): List<Quote> =
        randomQuotesService.get10RandomQuotes()

    override suspend fun get10RandomQuotesByAnimeTitle(title: String): List<Quote> =
        randomQuotesService.get10RandomQuotesByAnimeTitle(title)

    override suspend fun get10RandomQuotesByCharacterName(name: String): List<Quote> =
        randomQuotesService.get10RandomQuotesByCharacterName(name)
}