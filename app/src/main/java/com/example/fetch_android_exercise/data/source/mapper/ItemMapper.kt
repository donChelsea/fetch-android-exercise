package com.example.fetch_android_exercise.data.source.mapper

import com.example.fetch_android_exercise.data.source.models.ItemDto
import com.example.fetch_android_exercise.domain.models.Item

object ItemMapper {

    fun ItemDto.toDomain() = Item(
        id = id,
        listId = listId,
        name = name,
    )
}