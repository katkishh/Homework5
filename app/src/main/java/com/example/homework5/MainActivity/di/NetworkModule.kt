package com.example.homework5.MainActivity.di

import com.example.homework5.MainActivity.data.remote.NanoPostApiService
import com.example.homework5.MainActivity.data.remote.PrefStorage
import com.example.homework5.MainActivity.data.remote.model.NanoPostAuthApiService
import com.example.homework5.MainActivity.data.remote.repository.NanoRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.get
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://nanopost.evolitist.com/"

    @Qualifier annotation class AuthClient

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient
    ): Retrofit{
        val json = Json{ignoreUnknownKeys = true}
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))     //MediaType.get("application/json")
            .build()
    }

    @Provides
    @Singleton
    @AuthClient
    fun provideAuthRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @AuthClient
    fun provideJson(
        json:Converter.Factory
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json)
            .build()
    }

    @Provides
    fun provideJsonConverter():Converter.Factory{
        return Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideNanopostApiService(
        retrofit: Retrofit
    ): NanoPostApiService{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideNanopostAuthApiService(
        @AuthClient retrofit: Retrofit,
    ): NanoPostAuthApiService{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        prefStorage: PrefStorage,
    ): Interceptor{
        return Interceptor { chain ->
            val request  = chain.request().newBuilder()
            request.addHeader(
                "Authorization",
                "Bearer ${prefStorage.token}"
            )
            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: Interceptor
    ): OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }
}