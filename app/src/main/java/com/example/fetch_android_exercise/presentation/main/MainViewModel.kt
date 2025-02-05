package com.example.fetch_android_exercise.presentation.main

import com.example.fetch_android_exercise.common.Resource
import com.example.fetch_android_exercise.presentation.usecases.GetItemsUseCase
import com.example.fetch_android_exercise.presentation.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getItemsUseCase: GetItemsUseCase,
) : BaseViewModel<MainUiState, MainUiEvent, MainUiAction>() {

    private val _state = MutableStateFlow(MainUiState())
    override val state: StateFlow<MainUiState>
        get() = _state.asStateFlow()

    override fun handleAction(action: MainUiAction) {

    }

    fun refresh() {
        safeLaunch {
            getItemsUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Error -> println(result.message.toString())
                    is Resource.Loading -> {}
                    is Resource.Success -> println(result.data.toString())
                }
            }
        }
    }
}