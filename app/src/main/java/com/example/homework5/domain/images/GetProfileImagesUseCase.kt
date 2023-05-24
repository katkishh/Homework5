package com.example.homework5.domain.images

import androidx.paging.PagingData
import com.example.homework5.data.model.Image
import com.example.homework5.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileImagesUseCase @Inject constructor(
    private val repository: ImageRepositoryImpl
) {
    suspend fun execute(profileId: String): Flow<PagingData<Image>>{
        return repository.getProfileImages(profileId)
    }
}