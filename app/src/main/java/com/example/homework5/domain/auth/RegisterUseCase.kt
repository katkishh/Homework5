package com.example.homework5.domain.auth

import com.example.homework5.data.model.remote.RegistrationRequest
import com.example.homework5.data.model.remote.TokenResponse
import com.example.homework5.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepositoryImpl
) {
    suspend fun execute(username: String, password: String): TokenResponse{
        return repository.register(username, password)
    }
}