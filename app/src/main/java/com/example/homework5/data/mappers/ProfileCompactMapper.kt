package com.example.homework5.data.mappers

import com.example.homework5.data.model.ApiProfileCompact
import com.example.homework5.data.model.ProfileCompact
import javax.inject.Inject

class ProfileCompactMapper @Inject constructor(

) {
    fun toCompact(apiModel: ApiProfileCompact) = ProfileCompact(
        id = apiModel.id,
        username = apiModel.username,
        displayName = apiModel.displayName,
        avatarUrl = apiModel.avatarUrl,
        subscribed = apiModel.subscribed
    )
}