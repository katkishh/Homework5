package com.example.homework5.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.homework5.data.remote.NanoPostApiService
import com.example.homework5.data.mappers.PostMapper
import com.example.homework5.data.model.Post
import com.example.homework5.data.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val nanoPostApiService: NanoPostApiService,
    private val postMapper: PostMapper,
): ProfileRepository {
    override suspend fun getProfilePosts(profileId: String): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(nanoPostApiService, profileId) }
        ).flow.map { pagingData ->
            pagingData.map { postMapper.toPost(it) }
        }
    }
}