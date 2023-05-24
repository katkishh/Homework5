package com.example.homework5.domain.images

import com.example.homework5.data.model.Image
import com.example.homework5.data.repository.ImageRepositoryImpl
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val repository: ImageRepositoryImpl
) {
    suspend fun execute(imageId:String): Image{
        return repository.getImage(imageId)
    }
}