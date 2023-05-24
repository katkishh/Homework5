package com.example.homework5.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.homework5.data.remote.NanoPostApiService
import com.example.homework5.data.mappers.ImageMapper
import com.example.homework5.data.model.Image
import com.example.homework5.data.paging.ImagesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val nanoPostApiService: NanoPostApiService,
    private val imageMapper: ImageMapper,
) : ImageRepository {
    override suspend fun uploadImage(image: ByteArray): Image {
        val image0 = MultipartBody.Part.createFormData(
            "image0",
            "image0.jpg",
            image.toRequestBody("image/*".toMediaType())
        )
        return imageMapper.toImage(nanoPostApiService.uploadImage(image0))
    }

    override suspend fun getProfileImages(profileId: String): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = {ImagesPagingSource(nanoPostApiService, profileId)}
        ).flow.map { pagingData ->
            pagingData.map { apiImage ->
                imageMapper.toImage(apiImage)
            }
        }
    }

    override suspend fun getImage(imageId: String): Image {
        return imageMapper.toImage(nanoPostApiService.getImage(imageId))
    }

    override suspend fun deleteImage(imageId: String): Boolean {
        return nanoPostApiService.deleteImage(imageId).result
    }


}