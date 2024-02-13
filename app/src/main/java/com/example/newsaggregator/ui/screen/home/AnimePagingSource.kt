package com.example.newsaggregator.ui.screen.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.repository.AnimeRepository

private const val LIMIT = 25

class AnimePagingSource(
    val animeRepository: AnimeRepository,
    val defaultLimit: Int = LIMIT,
) : PagingSource<Int, Anime>() {
    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val result = try {
            val currentPage = params.key ?: 1
            val data = animeRepository.getAnimeList(
                page = currentPage,
                limit = defaultLimit
            )

            val nextKey = when {
                data.isEmpty() -> null
                data.size < defaultLimit -> null
                else -> currentPage.plus(1)
            }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
        return result
    }
}