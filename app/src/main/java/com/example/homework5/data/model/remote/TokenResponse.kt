package com.example.homework5.data.model.remote

@kotlinx.serialization.Serializable
data class TokenResponse(
    val token:String,
    val userId:String,
)