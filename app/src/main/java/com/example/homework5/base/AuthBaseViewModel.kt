package com.example.homework5.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5.data.remote.AuthResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class AuthBaseViewModel: ViewModel() {
    fun <T> MutableLiveData<AuthResult<T>>.loadData(
        block: suspend () -> T,
    ) {
        viewModelScope.launch {
            flow {
                try {
                    emit(AuthResult.Validate())
                    emit(AuthResult.Success(block()))
                } catch (t: UnknownHostException) {
                    emit(AuthResult.NetworkError(t))
                } catch (t: HttpException) {
                    emit(AuthResult.PasswordError(t))
                } catch (t: Throwable){
                    emit(AuthResult.Error(t))
                }
            }.collect{ result ->
                postValue(result)
            }
        }
    }
}