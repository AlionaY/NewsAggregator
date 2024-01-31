package com.example.newsaggregator.ui.screen.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsaggregator.data.Anime
import com.example.newsaggregator.repository.AnimeRepository

class AnimePagingSource(
    val animeRepository: AnimeRepository,
    val query: String = ""
) : PagingSource<Int, Anime>() {
    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val nextPage = params.key ?: 1
        val response = animeRepository.getAnimeList(page = nextPage, limit = 10, query = query)
        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = nextPage.plus(1)
        )
    }
}