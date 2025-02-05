package com.example.fetch_android_exercise.domain.repository

import com.example.fetch_android_exercise.common.Resource
import com.example.fetch_android_exercise.domain.models.ItemGroup
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getItems(): Flow<Resource<List<ItemGroup>>>
}