package com.example.homework5.MainActivity.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.homework5.MainActivity.data.remote.NanoPostApiService
import com.example.homework5.MainActivity.data.remote.mappers.PostMapper
import com.example.homework5.MainActivity.data.remote.model.Post
import com.example.homework5.MainActivity.data.remote.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepository(
    private val nanoPostApiService: NanoPostApiService,
    private val postMapper: PostMapper,
) {

    fun getProfilePosts():Flow<PagingData<Post>>{
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),            //это дефолтное значение колва постов
            pagingSourceFactory = {PostPagingSource(nanoPostApiService)}
        ).flow.map { pagingData ->
            pagingData.map { postMapper.toPost(it) }
        }
    }
}