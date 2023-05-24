package com.example.homework5.data.repository

import androidx.paging.PagingData
import com.example.homework5.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost(postId: String): Post

    suspend fun createPost(text: String?, list:List<ByteArray>?): Post

    suspend fun getFeed(): Flow<PagingData<Post>>

    suspend fun getProfilePost(profileId:String): Flow<PagingData<Post>>
}