package com.example.fetch_android_exercise.data.source.mapper

import com.example.fetch_android_exercise.data.source.model.ItemDto
import com.example.fetch_android_exercise.domain.models.Item
import com.example.fetch_android_exercise.domain.models.ItemGroup

object ItemMapper {

    fun toDomain(items: List<ItemDto>): List<ItemGroup> =
        with(items) {
            filter { item -> !item.name.isNullOrEmpty() }
                .groupBy { item -> item.listId }
                .toSortedMap()
                .map { items -> toDomain(items) }
        }

    private fun toDomain(item: ItemDto): Item =
        with(item) {
            Item(
                id = id,
                listId = listId,
                name = name.orEmpty(),
            )
        }

    private fun toDomain(groupedMap: Map.Entry<Int, List<ItemDto>>): ItemGroup =
        with(groupedMap) {
            ItemGroup(
                id = key,
                items = value
                    .map { item -> toDomain(item) }
                    .sortedBy { item -> item.name.split(" ").last().toInt() }
            )
        }
}