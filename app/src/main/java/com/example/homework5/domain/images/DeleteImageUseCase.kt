package com.example.homework5.domain.images

import com.example.homework5.data.repository.ImageRepositoryImpl
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(
    private val repository: ImageRepositoryImpl
) {
    suspend fun execute(imageId:String): Boolean{
        return repository.deleteImage(imageId)
    }
}