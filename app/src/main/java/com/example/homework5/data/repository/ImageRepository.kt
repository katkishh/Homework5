package com.example.homework5.data.repository

import androidx.paging.PagingData
import com.example.homework5.data.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun uploadImage(image: ByteArray): Image

    suspend fun getProfileImages(profileId: String): Flow<PagingData<Image>>

    suspend fun getImage(imageId: String): Image

    suspend fun deleteImage(imageId:String): Boolean
}