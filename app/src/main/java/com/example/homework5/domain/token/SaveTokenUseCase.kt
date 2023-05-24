package com.example.homework5.domain.token

import com.example.homework5.data.model.remote.TokenResponse
import com.example.homework5.data.repository.TokenRepositoryImpl
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private  val repository: TokenRepositoryImpl
) {
    suspend fun execute(tokenResponse: TokenResponse){
        repository.saveToken(tokenResponse)
    }
}