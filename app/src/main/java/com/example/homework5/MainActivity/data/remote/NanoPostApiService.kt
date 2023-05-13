package com.example.homework5.MainActivity.data.remote

import com.example.homework5.MainActivity.data.remote.model.ApiPost
import com.example.homework5.MainActivity.data.remote.model.ApiProfile
import com.example.homework5.MainActivity.data.remote.model.PagedDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NanoPostApiService {
    @GET("api/v1/profile/{profileId}")
    suspend fun getProfile(
        @Path("profileId")profileId:String,
    ): ApiProfile

    @GET("posts/{profileId}")
    fun getProfilePosts(
        @Path("profileId")profileId:String,
        @Query("count") count:Int,
        @Query("offset") offset:String?,
    ): PagedDataResponse<ApiPost>
}