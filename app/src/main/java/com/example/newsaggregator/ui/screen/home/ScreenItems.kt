package com.example.newsaggregator.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.newsaggregator.data.Anime

@Composable
fun AnimeList(
    animeList: LazyPagingItems<Anime>,
    modifier: Modifier = Modifier,
    onAnimeItemClick: (Anime) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        items(
            count = animeList.itemCount,
            key = animeList.itemKey { it.id }
        ) { index ->
            val item = animeList[index]

            AnimeItem(
                iconUrl = item?.images?.jpg?.imageUrl.orEmpty(),
                title = item?.title.orEmpty(),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                item?.let { onAnimeItemClick(it) }
            }
        }

        item {
            if (animeList.loadState.append == LoadState.Loading) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 4.dp
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(10.dp)) }
    }
}

@Composable
private fun AnimeItem(
    iconUrl: String,
    title: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    val shape = RoundedCornerShape(10)
    Card(
        modifier = modifier
            .clip(shape)
            .clickable { onItemClick() },
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SubcomposeAsyncImage(
                    model = iconUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)
                        .clip(RoundedCornerShape(10)),
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(70.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 10.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

