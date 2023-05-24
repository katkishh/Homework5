package com.example.homework5.data.mappers

import com.example.homework5.data.model.Post
import com.example.homework5.data.model.ApiPost
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val imagesMapper: ImageMapper,
    private val profileCompactMapper: ProfileCompactMapper
) {
    fun toPost(apiModel: ApiPost) = Post(
        id = apiModel.id,
        owner = profileCompactMapper.toCompact(apiModel.owner),
        dataCreated = apiModel.dataCreated,
        text = apiModel.text,
        images = apiModel.images?.map(imagesMapper::toImage)
    )
}