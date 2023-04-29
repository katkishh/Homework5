package com.example.homework5.MainActivity.di

import com.example.homework5.MainActivity.data.remote.repository.NanoRepository
import com.example.homework5.MainActivity.data.remote.repository.NanoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl:NanoRepositoryImpl): NanoRepository
}