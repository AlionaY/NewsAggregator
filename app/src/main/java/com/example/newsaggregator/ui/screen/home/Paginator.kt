package com.example.newsaggregator.ui.screen.home

import android.util.Log

interface Paginator<Key, Anime> {
    suspend fun loadNextPage()
    fun reset()
}

class DefaultPaginator<Int, Anime>(
    private val initialKey: Int,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Int) -> List<Anime>,
    private val getNextKey: suspend (List<Anime>) -> Int,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (items: List<Anime>, newKey: Int) -> Unit
) : Paginator<Int, Anime> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) return

        isMakingRequest = true
        onLoadUpdated(true)

        runCatching {
            Log.d("$$$", "run catching")
            onRequest(currentKey)
        }.onSuccess { list ->
            Log.d("$$$", "onsuccess ${list.size}")
            isMakingRequest = false
            currentKey = getNextKey(list)
            onSuccess(list, currentKey)
        }.onFailure {
            Log.d("$$$", "on fail ${it}")
            onError(it)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}