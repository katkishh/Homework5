package com.example.homework5.data.model

import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.model.remote.RegistrationRequest
import com.example.homework5.data.model.remote.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NanoPostAuthApiService {
    @GET("/api/auth/checkUsername")
    suspend fun checkUsername(
        @Query("username") username:String,
    ): CheckUserNameResult

    @GET("/api/auth/login")
    suspend fun authorize(
        @Query("username") username: String,
        @Query("password") password:String,
    ): TokenResponse

    @POST("api/auth/register")
    suspend fun register(
        @Body registrationRequest: RegistrationRequest
    ): TokenResponse
}