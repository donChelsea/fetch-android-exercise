package com.example.fetch_android_exercise.data.source

import com.example.fetch_android_exercise.data.source.model.ItemDto
import retrofit2.http.GET

interface ListApi {

    @GET("hiring.json")
    suspend fun getItems(): List<ItemDto>
}