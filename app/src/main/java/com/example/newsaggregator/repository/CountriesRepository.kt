package com.example.newsaggregator.repository

import com.example.newsaggregator.data.Country
import com.example.newsaggregator.network.service.CountriesService
import javax.inject.Inject

interface CountriesRepository {
    suspend fun getAllCountries() : List<Country>
}

class CountriesRepositoryImpl @Inject constructor(
    private val countriesService: CountriesService
): CountriesRepository {
    override suspend fun getAllCountries(): List<Country> = countriesService.getAllCountries().data

}