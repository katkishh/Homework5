package com.example.homework5.MainActivity.data.remote.model

import com.example.homework5.MainActivity.data.remote.model.remote.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface NanoPostAuthApiService {
    fun checkUsername()

    fun authorize()

    @POST("api/auth/register")
    fun register(
        @Body registrationRequest: RegistrationRequest          //переведет в жсон-объект
    )
}