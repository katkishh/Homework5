package com.example.homework5.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework5.data.model.ApiImage
import com.example.homework5.data.remote.NanoPostApiService
import java.lang.Exception

class ImagesPagingSource constructor(
    private val apiService: NanoPostApiService,
    private val profileId: String
): PagingSource<String, ApiImage>() {
    override fun getRefreshKey(state: PagingState<String, ApiImage>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiImage> {
        return try {
            val response = apiService.getProfileImages(
                profileId = profileId,
                count = params.loadSize,
                offset = params.key,
            )
            LoadResult.Page(
                data = response.item,
                nextKey = response.offset,
                prevKey = null
            )
        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

}