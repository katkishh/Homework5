package com.example.homework5.MainActivity.data.remote

import com.example.homework5.MainActivity.data.remote.model.ApiProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface NanoPostApiService {
    @GET("api/v1/profile/{profileId}")
    suspend fun getProfile(
        @Path("profileId")profileId:String,
    ): ApiProfile
}