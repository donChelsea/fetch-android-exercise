@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fetch_android_exercise.presentation.main.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetch_android_exercise.presentation.components.screens.ShowError
import com.example.fetch_android_exercise.presentation.components.screens.ShowLoading
import com.example.fetch_android_exercise.presentation.main.MainUiAction
import com.example.fetch_android_exercise.presentation.main.MainUiState
import com.example.fetch_android_exercise.presentation.main.MainViewModel
import com.example.fetch_android_exercise.presentation.main.ScreenState
import com.example.fetch_android_exercise.presentation.models.ItemGroupUiModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    MainLayout(
        state = state,
        onAction = viewModel::handleAction,
        modifier = modifier,
    )
}

@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    state: MainUiState,
    onAction: (MainUiAction) -> Unit,
) {
    when (state.screenState) {
        ScreenState.Initial -> {}
        ScreenState.Loading -> ShowLoading()
        is ScreenState.Error -> ShowError(message = state.screenState.message)
        is ScreenState.Data -> MainContent(
            groups = state.screenState.items,
            onAction = onAction,
            modifier = modifier,
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    groups: List<ItemGroupUiModel>,
    onAction: (MainUiAction) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        groups.forEach { group ->
            stickyHeader {
                GroupHeader(title = group.id.toString())
            }
            items(group.items) { item ->
                GroupItem(title = item.name)
            }
        }
    }
}

@Composable
private fun GroupHeader(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        text = "Group $title",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
private fun GroupItem(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        text = title,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}