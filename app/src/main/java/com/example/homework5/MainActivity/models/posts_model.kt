package com.example.homework5.MainActivity.models

import java.util.UUID

data class PostsModel(
    val id:String = UUID.randomUUID().toString(),
    val userName: String,
    val postDate: String,
    val postDescription: String,
    val postIMG: String,
)