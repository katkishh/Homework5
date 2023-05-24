package com.example.homework5.data.repository

import com.example.homework5.data.model.remote.TokenResponse
import com.example.homework5.data.remote.PrefStorage
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val prefStorage: PrefStorage
): TokenRepository {
    override suspend fun saveToken(token: TokenResponse) {
        prefStorage.token = token.token
    }

    override suspend fun getToken(): String? {
        return prefStorage.token
    }

    override suspend fun getUserId(): String? {
        return prefStorage.userId
    }

    override suspend fun deleteToken() {
        prefStorage.token = null
        prefStorage.userId = null
    }
}