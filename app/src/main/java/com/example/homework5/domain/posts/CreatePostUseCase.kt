package com.example.homework5.domain.posts

import com.example.homework5.data.model.Post
import com.example.homework5.data.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(text:String?, list: List<ByteArray>?): Post {
        return postRepository.createPost(text, list)
    }
}