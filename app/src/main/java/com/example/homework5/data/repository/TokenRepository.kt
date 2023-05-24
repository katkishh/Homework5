package com.example.homework5.data.repository

import com.example.homework5.data.model.remote.TokenResponse

interface TokenRepository {
    suspend fun saveToken(token: TokenResponse)
    suspend fun getToken(): String?
    suspend fun getUserId(): String?
    suspend fun deleteToken()
}