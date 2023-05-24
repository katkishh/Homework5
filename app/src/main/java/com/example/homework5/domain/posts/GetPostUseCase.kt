package com.example.homework5.domain.posts

import com.example.homework5.data.model.Post
import com.example.homework5.data.repository.PostRepositoryImpl
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepositoryImpl
) {
    suspend fun execute(postId: String): Post{
        return repository.getPost(postId)
    }
}