package com.example.homework5.data.mappers

import com.example.homework5.data.model.ApiImage
import com.example.homework5.data.model.Image
import javax.inject.Inject

class ImageMapper @Inject constructor(
    private val profileCompactMapper: ProfileCompactMapper,
    private val imageSizesMapper: ImageSizesMapper
) {

    fun toImage(apiModel:ApiImage) = Image(
        id = apiModel.id,
        sizes = apiModel.sizes.map { imageSizesMapper.toImageSize(it) },
        owner = profileCompactMapper.toCompact(apiModel.owner),
        dateCreated = apiModel.dateCreated
    )
}