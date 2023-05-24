package com.example.homework5.data.model

data class Image(
    val id: String,
    val owner: ProfileCompact,
     val dateCreated: String,
    val sizes: List<ImageSize>
)