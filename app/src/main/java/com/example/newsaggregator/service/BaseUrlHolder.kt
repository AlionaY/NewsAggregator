package com.example.newsaggregator.service

import com.example.newsaggregator.BuildConfig

data class BaseUrlHolder(var url: String = BuildConfig.REST_URL)