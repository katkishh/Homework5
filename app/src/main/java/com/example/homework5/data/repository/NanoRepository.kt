package com.example.homework5.data.repository

import com.example.homework5.data.model.Profile

interface NanoRepository {
    suspend fun getProfile(profileId:String): Profile
}