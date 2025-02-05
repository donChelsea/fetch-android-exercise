package com.example.fetch_android_exercise.presentation.main

import androidx.compose.runtime.Immutable
import com.example.fetch_android_exercise.domain.models.Item

@Immutable
data class MainUiState(
    val screenState: ScreenState = ScreenState.Initial
)

@Immutable
sealed class MainUiEvent {
//    @Immutable
//    data class OnMovieClicked(val movieId: Int): HomeUiEvent()
}

@Immutable
sealed class MainUiAction {
//    @Immutable
//    data class OnMovieClicked(val movieId: Int): HomeUiAction()
}

@Immutable
sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()

    @Immutable
    data class Data(
        val items: List<Item> = emptyList(),
    ) : ScreenState()
}