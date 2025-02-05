package com.example.fetch_android_exercise.presentation.mapper

import com.example.fetch_android_exercise.domain.models.Item
import com.example.fetch_android_exercise.domain.models.ItemGroup
import com.example.fetch_android_exercise.presentation.models.ItemGroupUiModel
import com.example.fetch_android_exercise.presentation.models.ItemUiModel

object ItemUiModelMapper {
    fun toUiModel(itemGroup: ItemGroup): ItemGroupUiModel =
        with(itemGroup) {
            ItemGroupUiModel(
                id = id,
                items = items.map { toUiModel(it) }
            )
        }

    private fun toUiModel(item: Item): ItemUiModel =
        with(item) {
            ItemUiModel(
                id = id,
                listId = listId,
                name = name,
            )
        }
}
