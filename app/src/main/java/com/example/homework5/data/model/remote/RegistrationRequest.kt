package com.example.homework5.data.model.remote

@kotlinx.serialization.Serializable
data class RegistrationRequest(
    var userName:String,
    val password:String,
)
