package com.example.newsaggregator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.events
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.ui.model.AppEvent
import com.example.newsaggregator.ui.model.BottomNavBarItems
import com.example.newsaggregator.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    fun navigate(bottomBarItem: BottomNavBarItems) {
        viewModelScope.launch {
            events.emit(
                AppEvent.Navigate(bottomBarItem.route) {
                    launchSingleTop = true
                    popUpTo(Routes.Home.route)
                }
            )
        }
    }
}