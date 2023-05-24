package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class ApiProfileCompact(
    val id:String,
    val username: String,
    val displayName: String? = null,
    val avatarUrl: String? = null,
    val subscribed: Boolean,
)
