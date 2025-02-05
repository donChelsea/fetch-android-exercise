package com.example.fetch_android_exercise.data.source.models

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("listId")
    val listId: Int,
    @SerializedName("name")
    val name: String?,
)
