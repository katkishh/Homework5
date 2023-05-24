package com.example.homework5.domain.auth

import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.repository.AuthRepository
import javax.inject.Inject

class CheckUserNameUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun execute(username:String): CheckUserNameResult{
        return repository.checkUsername(username)
    }
}