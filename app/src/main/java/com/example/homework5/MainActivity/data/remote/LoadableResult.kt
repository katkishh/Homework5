package com.example.homework5.MainActivity.data.remote

sealed class LoadableResult<T> {         //обертка для запросов всех типов экранов
    class Loading<T>: LoadableResult<T>()

    data class Success<T>(              //делаем generic-классом
        val value: T
    ): LoadableResult<T>()

    data class Error<T>(
        val throwable: Throwable
    ): LoadableResult<T>()
}
