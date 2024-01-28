package com.example.newsaggregator.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsaggregator.ui.theme.Black

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {
//    todo: add button for search a random quote
//    todo: add ability to write an anime title/name to search quotes

    val quotes by viewModel.randomQuotes.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        if (loadingState == LoadingState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                    onClick = { viewModel.getAllRandomQuotes() }) {
                    Text(text = "Get random quote")
                }

            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(quotes) { quote ->

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                        text = quote.quote,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Black
                    )
                }
            }
        }
    }
}