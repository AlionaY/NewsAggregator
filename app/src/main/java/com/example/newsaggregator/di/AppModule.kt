package com.example.newsaggregator.di

import com.example.newsaggregator.network.BaseUrlHolder
import com.example.newsaggregator.network.client.ApiClient
import com.example.newsaggregator.network.service.AnimeService
import com.example.newsaggregator.network.service.AnimeServiceImpl
import com.example.newsaggregator.network.service.GenresService
import com.example.newsaggregator.network.service.GenresServiceImpl
import com.example.newsaggregator.network.service.RandomQuotesService
import com.example.newsaggregator.network.service.RandomQuotesServiceImpl
import com.example.newsaggregator.repository.AnimeRepository
import com.example.newsaggregator.repository.AnimeRepositoryImpl
import com.example.newsaggregator.repository.GenresRepository
import com.example.newsaggregator.repository.GenresRepositoryImpl
import com.example.newsaggregator.repository.RandomQuotesRepository
import com.example.newsaggregator.repository.RandomQuotesRepositoryImpl
import com.example.newsaggregator.ui.screen.home.AnimePagingSource
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

    @Singleton
    @Provides
    fun provideGenresService(apiClient: ApiClient): GenresService = GenresServiceImpl(apiClient)

    @Singleton
    @Provides
    fun provideGenresRepository(service: GenresService): GenresRepository =
        GenresRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideAnimeService(apiClient: ApiClient): AnimeService = AnimeServiceImpl(apiClient)

    @Singleton
    @Provides
    fun provideAnimeRepository(service: AnimeService): AnimeRepository =
        AnimeRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideAnimePagingSource(repo: AnimeRepository) = AnimePagingSource(repo)
}