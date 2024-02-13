package com.example.newsaggregator.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.newsaggregator.R

@Composable
fun ErrorDialog(
    text: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(10)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.oops),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            }
        }
    }
}