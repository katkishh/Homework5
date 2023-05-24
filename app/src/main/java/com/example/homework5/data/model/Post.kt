package com.example.homework5.data.model

data class Post (
    val id:String,
    val owner:ProfileCompact,
    val dataCreated:Long,
    val text:String? = null,
    val images: List<Image>? = null,
)
