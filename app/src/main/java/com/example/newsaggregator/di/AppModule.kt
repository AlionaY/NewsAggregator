package com.example.newsaggregator.di

import com.example.newsaggregator.network.BaseUrlHolder
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.service.CountriesService
import com.example.newsaggregator.network.service.CountriesServiceImpl
import com.example.newsaggregator.repository.CountriesRepository
import com.example.newsaggregator.repository.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBAseUrlHolder() = BaseUrlHolder()

    @Singleton
    @Provides
    fun provideCountriesService(apiClient: ApiClient): CountriesService =
        CountriesServiceImpl(apiClient)

    @Singleton
    @Provides
    fun provideCountriesRepository(countriesService: CountriesService): CountriesRepository =
        CountriesRepositoryImpl(countriesService)
}