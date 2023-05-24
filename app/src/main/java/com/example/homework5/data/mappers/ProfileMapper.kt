package com.example.homework5.data.mappers

import com.example.homework5.data.model.ApiProfile
import com.example.homework5.data.model.ApiProfileCompact
import com.example.homework5.data.model.Profile
import com.example.homework5.data.model.ProfileCompact
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {
    fun convert(apiModel: ApiProfile) = Profile(
        id = apiModel.id,
        userName = apiModel.userName,
        displayName = apiModel.displayName,
        bio = apiModel.bio,
        avatarId = apiModel.avatarId,
        avatarSmall = apiModel.avatarSmall,
        avatarLarge = apiModel.avatarLarge,
        subscribed = apiModel.subscribed,
        subscribersCount = apiModel.subscribersCount,
        imagesCount = apiModel.imagesCount,
        postsCount = apiModel.postsCount,
        images = apiModel.images.map { imageMapper.toImage(it) },
    )
}