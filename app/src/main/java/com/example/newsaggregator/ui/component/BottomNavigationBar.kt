package com.example.newsaggregator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.ui.model.BottomNavBarItems
import com.example.newsaggregator.ui.theme.Black
import com.example.newsaggregator.ui.theme.Typography

@Composable
fun BottomNavigationBar(
    onItemSelected: (BottomNavBarItems) -> Unit,
) {
    var selectedItem by remember { mutableStateOf(BottomNavBarItems.Home) }

    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NavBarItem(
            selectedItem = selectedItem,
            onItemSelected = {
                onItemSelected(it)
                selectedItem = it
            }
        )
    }
}

@Composable
private fun RowScope.NavBarItem(
    selectedItem: BottomNavBarItems,
    onItemSelected: (BottomNavBarItems) -> Unit
) {
    BottomNavBarItems.entries.forEach { item ->
        NavigationBarItem(
            selected = selectedItem == item,
            onClick = { onItemSelected(item) },
            icon = {
                val icon = if (selectedItem == item) item.selectedIcon else item.unselectedIcon
                Image(
                    imageVector = icon,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(id = item.title),
                    style = Typography.labelSmall,
                    color = Black
                )
            }
        )
    }
}