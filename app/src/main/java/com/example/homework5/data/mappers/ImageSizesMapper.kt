package com.example.homework5.data.mappers

import com.example.homework5.data.model.ApiImageSize
import com.example.homework5.data.model.ImageSize
import javax.inject.Inject

class ImageSizesMapper @Inject constructor() {
    fun toImageSize(apiModel: ApiImageSize) = ImageSize(
        width = apiModel.width,
        height = apiModel.height,
        url = apiModel.url
    )

    fun toApiImageSize(imgSize: ImageSize) = ApiImageSize(
        width = imgSize.width,
        height = imgSize.height,
        url = imgSize.url
    )
}