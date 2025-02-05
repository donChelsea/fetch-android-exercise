package com.example.fetch_android_exercise.presentation.main

import androidx.compose.runtime.Immutable
import com.example.fetch_android_exercise.presentation.models.ItemGroupUiModel

@Immutable
data class MainUiState(
    val screenState: ScreenState = ScreenState.Initial
)

@Immutable
sealed class MainUiEvent

@Immutable
sealed class MainUiAction

@Immutable
sealed class ScreenState {
    data object Initial : ScreenState()
    data object Loading : ScreenState()
    @Immutable
    data class Error(val message: String) : ScreenState()
    @Immutable
    data class Data(
        val items: List<ItemGroupUiModel> = emptyList(),
    ) : ScreenState()
}