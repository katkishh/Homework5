package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class ApiPost(
    val id:String,
    val owner: ApiProfileCompact,
    val dataCreated:Long,
    val text:String? = null,
    val images: List<ApiImage>? = null,
)