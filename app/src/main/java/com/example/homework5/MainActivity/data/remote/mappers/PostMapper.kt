package com.example.homework5.MainActivity.data.remote.mappers

import com.example.homework5.MainActivity.data.remote.model.Post
import com.example.homework5.MainActivity.data.remote.model.ApiPost
import javax.inject.Inject

class PostMapper @Inject constructor() {
    fun toPost(apiModel: ApiPost) = Post(
        id = apiModel.id,
        owner = apiModel.owner,
        dataCreated = apiModel.dataCreated,
        text = apiModel.text
    )

    fun toApiPost(post: Post) = ApiPost(
        id = post.id,
        owner = post.owner,
        dataCreated = post.dataCreated,
        text = post.text
    )
}