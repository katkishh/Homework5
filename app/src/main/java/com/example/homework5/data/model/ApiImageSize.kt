package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class ApiImageSize(
    val width: Int,
    val height: Int,
    val url: String
)
