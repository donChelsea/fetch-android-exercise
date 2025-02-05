package com.example.fetch_android_exercise.data.repository

import com.example.fetch_android_exercise.common.Resource
import com.example.fetch_android_exercise.data.source.ListApi
import com.example.fetch_android_exercise.data.source.mapper.ItemMapper.toDomain
import com.example.fetch_android_exercise.domain.models.Item
import com.example.fetch_android_exercise.domain.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject constructor(
    val api: ListApi,
) : ItemRepository {

    override suspend fun getItems(): Flow<Resource<List<Item>>> =
        flow {
            emit(Resource.Loading())

            val items = api.getItems()
            emit(Resource.Success(data = items.map { it.toDomain() }))
        }.catch { exception ->
            emit(Resource.Error(message = exception.message.toString()))
        }.flowOn(Dispatchers.IO)
}