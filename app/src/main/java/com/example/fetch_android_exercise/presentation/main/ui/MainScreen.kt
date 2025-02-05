@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fetch_android_exercise.presentation.main.ui

import android.content.res.Configuration
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetch_android_exercise.R
import com.example.fetch_android_exercise.presentation.components.screens.ShowError
import com.example.fetch_android_exercise.presentation.components.screens.ShowLoading
import com.example.fetch_android_exercise.presentation.main.MainUiState
import com.example.fetch_android_exercise.presentation.main.MainViewModel
import com.example.fetch_android_exercise.presentation.main.ScreenState
import com.example.fetch_android_exercise.presentation.models.ItemGroupUiModel
import com.example.fetch_android_exercise.ui.theme.FetchandroidexerciseTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    MainLayout(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    state: MainUiState,
) {
    when (state.screenState) {
        ScreenState.Initial -> {}
        ScreenState.Loading -> ShowLoading()
        is ScreenState.Error -> ShowError(message = state.screenState.message)
        is ScreenState.Data -> MainContent(
            modifier = modifier,
            groups = state.screenState.items,
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    groups: List<ItemGroupUiModel>,
) {
    LazyColumn(modifier = modifier) {
        groups.forEach { group ->
            stickyHeader {
                GroupHeader(
                    title = stringResource(R.string.group_header_title, group.id)
                )
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
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(dimensionResource(R.dimen.item_padding))
    )
}

@Composable
private fun GroupItem(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(R.dimen.item_padding))
    )
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GroupHeaderPreview() {
    FetchandroidexerciseTheme {
        GroupHeader(title = "Group 3")
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GroupItemPreview() {
    FetchandroidexerciseTheme {
        GroupItem(title = "Item 101")
    }
}