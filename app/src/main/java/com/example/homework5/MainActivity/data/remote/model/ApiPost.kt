package com.example.homework5.MainActivity.data.remote.model

@kotlinx.serialization.Serializable
data class ApiPost(
    val id:String,
    val owner: ApiProfile,
    val dataCreated:Int,
    val text:String?,
)