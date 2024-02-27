package androidx.lifecycle

import com.example.newsaggregator.ui.model.AppEvent
import kotlinx.coroutines.flow.MutableSharedFlow

private const val EVENTS_KEY = "com.example.newsaggregator.EVENTS_KEY"

val ViewModel.events: MutableSharedFlow<AppEvent>
    get() = getTag(EVENTS_KEY) ?: setTagIfAbsent(
        EVENTS_KEY,
        MutableSharedFlow(extraBufferCapacity = 1)
    )