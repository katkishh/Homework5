package com.example.homework5.MainActivity.data.remote.mappers

import com.example.homework5.MainActivity.data.remote.model.ApiProfile
import com.example.homework5.MainActivity.data.remote.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {
    fun convert(apiModel:ApiProfile) = Profile(
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
    )
}