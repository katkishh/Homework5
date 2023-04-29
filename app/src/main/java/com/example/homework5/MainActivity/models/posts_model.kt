package com.example.homework5

import android.os.Parcelable
import java.util.UUID
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsModel(
    val id:String = UUID.randomUUID().toString(),
    val userName: String,
    val postDate: String,
    val postDescription: String,
    val postIMG: String,
) : Parcelable