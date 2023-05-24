package com.example.homework5.data.model.remote

@kotlinx.serialization.Serializable
data class AuthRequest(
    var username:String,
    var password:String,
)