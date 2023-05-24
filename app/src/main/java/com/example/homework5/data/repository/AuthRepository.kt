package com.example.homework5.data.repository

import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.model.remote.RegistrationRequest
import com.example.homework5.data.model.remote.TokenResponse

interface AuthRepository {
    suspend fun checkUsername(username: String): CheckUserNameResult

    suspend fun authorize(username:String, password:String): TokenResponse

    suspend fun register(username: String, password: String):TokenResponse
}