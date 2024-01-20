package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Country
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.Response

private const val ALL_COUNTRIES = "all"

interface CountriesService {
    suspend fun getAllCountries(): DataResponse<List<Country>>
}

class CountriesServiceImpl(private val apiClient: ApiClient) : CountriesService {
    override suspend fun getAllCountries(): DataResponse<List<Country>> =
        apiClient.get(ALL_COUNTRIES)

}