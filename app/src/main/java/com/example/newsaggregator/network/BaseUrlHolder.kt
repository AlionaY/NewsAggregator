package com.example.newsaggregator.network

import com.example.newsaggregator.BuildConfig

data class BaseUrlHolder(var url: String = BuildConfig.REST_URL)