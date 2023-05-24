package com.example.homework5.data.remote

sealed class LoadableResult<T> {
    class Loading<T>: LoadableResult<T>()

    data class Success<T>(
        val value: T
    ): LoadableResult<T>()

    data class Error<T>(
        val throwable: Throwable
    ): LoadableResult<T>()
}
