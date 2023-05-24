package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class ApiProfile(
    val id:String,
    val userName:String,
    val displayName:String? = null,
    val bio:String? = null,
    val avatarId:String? = null,
    val avatarSmall:String? = null,
    val avatarLarge:String? = null,
    val subscribed:Boolean,
    val subscribersCount:Int,
    val postsCount:Int,
    val imagesCount:Int,
    val images: List<ApiImage>,
)