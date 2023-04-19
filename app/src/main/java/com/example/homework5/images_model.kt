package com.example.homework5

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ImagesModel(
    val id:String = UUID.randomUUID().toString(),
    val image:String,
) : Parcelable