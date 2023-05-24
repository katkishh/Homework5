package com.example.homework5.domain

import com.example.homework5.data.model.Profile
import com.example.homework5.data.repository.NanoRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: NanoRepository,
) {
    suspend fun execute(profileId:String): Profile {
        return repository.getProfile(profileId)
    }
}