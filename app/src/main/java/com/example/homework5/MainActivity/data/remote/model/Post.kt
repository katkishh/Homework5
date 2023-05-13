package com.example.homework5.MainActivity.data.remote.model

data class Post (
    val id:String,
    val owner:ApiProfile,
    val dataCreated:Int,
    val text:String?,
)
