package com.example.fetch_android_exercise.presentation.usecases

import com.example.fetch_android_exercise.common.Resource
import com.example.fetch_android_exercise.domain.models.Item
import com.example.fetch_android_exercise.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Item>>> =
        repository.getItems()
}
