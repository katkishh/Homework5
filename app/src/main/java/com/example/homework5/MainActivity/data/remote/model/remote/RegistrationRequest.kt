package com.example.homework5.MainActivity.data.remote.model.remote

@kotlinx.serialization.Serializable
data class RegistrationRequest(
    var userName:String,
    val password:String,
)
