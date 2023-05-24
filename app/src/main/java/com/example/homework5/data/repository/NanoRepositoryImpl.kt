package com.example.homework5.data.repository

import com.example.homework5.data.remote.NanoPostApiService
import com.example.homework5.data.mappers.ProfileMapper
import com.example.homework5.data.model.Profile
import javax.inject.Inject

class NanoRepositoryImpl @Inject constructor(
    private val apiService: NanoPostApiService,
    private val profileMapper: ProfileMapper
): NanoRepository {
    override suspend fun getProfile(profileId:String): Profile {
        return profileMapper.convert(apiService.getProfile(profileId))
    }
}