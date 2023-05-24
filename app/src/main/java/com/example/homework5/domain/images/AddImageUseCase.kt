package com.example.homework5.domain.images

import com.example.homework5.data.model.Image
import com.example.homework5.data.repository.ImageRepository
import javax.inject.Inject


class AddImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun execute(image: ByteArray): Image{
        return imageRepository.uploadImage(image)
    }
}