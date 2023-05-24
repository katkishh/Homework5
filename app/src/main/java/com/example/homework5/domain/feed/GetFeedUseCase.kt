package com.example.homework5.domain.feed

import androidx.paging.PagingData
import com.example.homework5.data.model.Post
import com.example.homework5.data.repository.PostRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: PostRepositoryImpl
) {
    suspend fun execute(): Flow<PagingData<Post>>{
        return repository.getFeed()
    }
}