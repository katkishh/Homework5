package com.example.homework5.MainActivity.di

import com.example.homework5.MainActivity.data.remote.NanoPostApiService
import com.example.homework5.MainActivity.data.remote.repository.NanoRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.get
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://nanopost.evolitist.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val json = Json{ignoreUnknownKeys = true}
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))     //MediaType.get("application/json")
            .build()
    }

    @Provides
    @Singleton
    fun provideNanopostApiService(
        retrofit: Retrofit
    ): NanoPostApiService{
        return retrofit.create()
    }
}