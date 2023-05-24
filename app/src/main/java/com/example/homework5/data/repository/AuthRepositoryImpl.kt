package com.example.homework5.data.repository

import com.example.homework5.data.model.NanoPostAuthApiService
import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.model.remote.RegistrationRequest
import com.example.homework5.data.model.remote.TokenResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: NanoPostAuthApiService,
): AuthRepository {
    override suspend fun checkUsername(username: String): CheckUserNameResult {
        return authApiService.checkUsername(username)
    }

    override suspend fun authorize(username: String, password: String): TokenResponse {
        return authApiService.authorize(username, password)
    }

    override suspend fun register(username: String, password: String): TokenResponse {
        return authApiService.register(
            RegistrationRequest(
                userName = username,
                password = password
            )
        )
    }
}