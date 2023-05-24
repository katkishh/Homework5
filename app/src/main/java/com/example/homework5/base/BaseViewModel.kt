package com.example.homework5.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5.data.remote.LoadableResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    fun <T> MutableLiveData<LoadableResult<T>>.loadData(
        block: suspend () -> T,
    ) {
        viewModelScope.launch {
            flow {
                try {
                    emit(LoadableResult.Loading())
                    emit(LoadableResult.Success(block()))
                } catch (t: Throwable){
                    emit(LoadableResult.Error(t))
                }
            }.collect{ result ->
                postValue(result)
            }
        }
    }
}