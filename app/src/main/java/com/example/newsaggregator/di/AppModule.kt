package com.example.newsaggregator.di

import com.example.newsaggregator.network.BaseUrlHolder
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.service.RandomQuotesService
import com.example.newsaggregator.network.service.RandomQuotesServiceImpl
import com.example.newsaggregator.repository.RandomQuotesRepository
import com.example.newsaggregator.repository.RandomQuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBAseUrlHolder() = BaseUrlHolder()

    @Singleton
    @Provides
    fun provideApiClient(urlHolder: BaseUrlHolder) = ApiClient(urlHolder)

    @Singleton
    @Provides
    fun provideCountriesService(apiClient: ApiClient): RandomQuotesService =
        RandomQuotesServiceImpl(apiClient)

    @Singleton
    @Provides
    fun provideCountriesRepository(randomQuotesService: RandomQuotesService): RandomQuotesRepository =
        RandomQuotesRepositoryImpl(randomQuotesService)
}