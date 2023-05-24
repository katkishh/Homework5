package com.example.homework5.data.model

@kotlinx.serialization.Serializable
data class PagedDataResponse<T>(
    val count:Int,
    val total:Int,
    val offset:String?,
    val item:List<T>
)