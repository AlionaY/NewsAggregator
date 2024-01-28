package com.example.newsaggregator.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.R
import com.example.newsaggregator.ui.theme.Black
import com.example.newsaggregator.ui.theme.RollingStone
import com.example.newsaggregator.ui.theme.Typography

@Composable
fun InDevelopment(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(70.dp),
            imageVector = Icons.Sharp.Build,
            contentDescription = null,
            tint = RollingStone
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.in_development),
            color = Black,
            textAlign = TextAlign.Center,
            style = Typography.headlineMedium
        )
    }
}