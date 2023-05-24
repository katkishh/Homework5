package com.example.homework5.data.repository

import androidx.paging.PagingData
import com.example.homework5.data.model.Post
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfilePosts(profileId: String):Flow<PagingData<Post>>
}