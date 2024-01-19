package com.example.newsaggregator.network.service

import com.example.newsaggregator.data.Country
import com.example.newsaggregator.network.response.DataResponse
import com.example.newsaggregator.network.response.Response

interface CountriesService {
    suspend fun getAllCountries(): DataResponse<List<Country>>
}