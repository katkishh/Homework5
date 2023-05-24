package com.example.homework5.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework5.data.model.ApiPost
import com.example.homework5.data.remote.NanoPostApiService

class FeedPagingSource constructor(
    private val apiService: NanoPostApiService
): PagingSource<String, ApiPost>() {
    override fun getRefreshKey(state: PagingState<String, ApiPost>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiPost> {
        return try {
            val response = apiService.getFeed(
                count = params.loadSize,
                offset = params.key
            )
            LoadResult.Page(
                data = response.item,
                nextKey = response.offset,
                prevKey = null
            )
        }catch (e: java.lang.Exception){
            return LoadResult.Error(e)
        }
    }

}