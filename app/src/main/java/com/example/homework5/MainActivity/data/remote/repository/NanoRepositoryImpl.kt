package com.example.homework5.MainActivity.data.remote.repository

import com.example.homework5.MainActivity.data.remote.NanoPostApiService
import com.example.homework5.MainActivity.data.remote.mappers.ProfileMapper
import com.example.homework5.MainActivity.data.remote.model.Profile
import javax.inject.Inject

class NanoRepositoryImpl @Inject constructor(
    private val apiService: NanoPostApiService,
    private val profileMapper: ProfileMapper
):NanoRepository {
    override suspend fun getProfile(profileId:String):Profile{
        return profileMapper.convert(apiService.getProfile(profileId))
    }
}