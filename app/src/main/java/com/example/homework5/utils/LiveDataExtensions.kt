package com.example.homework5.utils


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.homework5.MainActivity.data.remote.LoadableResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
/*
fun <T> MutableLiveData<LoadableResult<T>>.loadData(
    coroutineScope: CoroutineScope,
    block: suspend () -> T,
) {
    coroutineScope.launch {
        flow {
            try {
                emit(LoadableResult.Loading())
                emit(LoadableResult.Success(block()))
            } catch (t: Throwable){
                emit(LoadableResult.Error(t))
            }
        }.collect{result ->             //создали флоу и сразу запускаем, потом сразу перекидывается в лайв-дату
            postValue(result)
        }
    }
}*/