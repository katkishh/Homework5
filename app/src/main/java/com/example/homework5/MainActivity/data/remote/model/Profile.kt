package com.example.homework5.MainActivity.data.remote.model

data class Profile (
    val id:String,
    val userName:String,
    val  displayName:String?,
    val bio:String?,
    val avatarId:String?,
    val avatarSmall:String?,
    val avatarLarge:String?,
    val subscribed:Boolean,
    val subscribersCount:Int,
    val postsCount:Int,
    val imagesCount:Int,
)

