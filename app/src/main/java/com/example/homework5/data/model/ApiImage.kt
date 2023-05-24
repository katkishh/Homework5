package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class ApiImage(
    val id: String,
    val owner: ApiProfileCompact,
    val dateCreated: String,
    val sizes: List<ApiImageSize>
)