package com.example.homework5.domain.token

import com.example.homework5.data.repository.TokenRepositoryImpl
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val repository: TokenRepositoryImpl
) {
    suspend fun execute(){
        repository.deleteToken()
    }
}