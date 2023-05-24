package com.example.homework5.domain.posts

import androidx.paging.PagingData
import com.example.homework5.data.model.Post
import com.example.homework5.data.repository.PostRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val repository: PostRepositoryImpl
) {
    suspend fun execute(profileId:String): Flow<PagingData<Post>>{
        return repository.getProfilePost(profileId)
    }
}