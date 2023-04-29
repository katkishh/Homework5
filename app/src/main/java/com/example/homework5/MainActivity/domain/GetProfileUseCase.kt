package com.example.homework5.MainActivity.domain

import com.example.homework5.MainActivity.data.remote.model.Profile
import com.example.homework5.MainActivity.data.remote.repository.NanoRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: NanoRepository,
) {
    suspend fun execute(profileId:String):Profile{
        return repository.getProfile(profileId)
    }
}