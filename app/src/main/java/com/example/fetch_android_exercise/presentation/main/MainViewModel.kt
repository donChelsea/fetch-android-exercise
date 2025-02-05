package com.example.fetch_android_exercise.presentation.main

import com.example.fetch_android_exercise.common.Resource
import com.example.fetch_android_exercise.presentation.mapper.ItemUiModelMapper
import com.example.fetch_android_exercise.presentation.usecases.GetItemsUseCase
import com.example.fetch_android_exercise.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : BaseViewModel<MainUiState, MainUiEvent, MainUiAction>() {

    private val _state = MutableStateFlow(MainUiState())
    override val state: StateFlow<MainUiState>
        get() = _state.asStateFlow()

    init {
        refresh()
    }

    override fun handleAction(action: MainUiAction) {}

    private fun refresh() {
        safeLaunch {
            getItemsUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Error -> updateState(ScreenState.Error(message = result.message.orEmpty()))
                    is Resource.Loading -> updateState(ScreenState.Loading)
                    is Resource.Success -> updateState(
                        ScreenState.Data(
                            items = result.data.orEmpty().map { ItemUiModelMapper.toUiModel(it) }
                        )
                    )
                }
            }
        }
    }

    private fun updateState(screenState: ScreenState) =
        _state.update { state ->
            state.copy(screenState)
        }
}