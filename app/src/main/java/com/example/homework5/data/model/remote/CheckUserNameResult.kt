package com.example.homework5.data.model.remote

@kotlinx.serialization.Serializable
enum class CheckUserNameResult{
    TooShort,
    TooLong,
    InvalidCharacters,
    Taken,
    Free
}