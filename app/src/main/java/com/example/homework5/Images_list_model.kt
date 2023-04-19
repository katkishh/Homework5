package com.example.homework5

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ImagesListModel(
    val id:String = UUID.randomUUID().toString(),
    val list:List<ImagesModel>,
    val listSize:Int = list.size,
) : Parcelable