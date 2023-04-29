package com.example.homework5.MainActivity.data.remote.repository

import com.example.homework5.MainActivity.data.remote.model.Profile

interface NanoRepository {
    suspend fun getProfile(profileId:String):Profile
}