package com.example.homework5.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework5.base.AuthBaseViewModel
import com.example.homework5.data.model.remote.CheckUserNameResult
import com.example.homework5.data.model.remote.TokenResponse
import com.example.homework5.data.remote.AuthResult
import com.example.homework5.domain.auth.AuthorizeUseCase
import com.example.homework5.domain.auth.CheckUserNameUseCase
import com.example.homework5.domain.auth.RegisterUseCase
import com.example.homework5.domain.token.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkUserNameUseCase: CheckUserNameUseCase,
    private val authorizeUseCase: AuthorizeUseCase,
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
): AuthBaseViewModel() {

    private val _checkUsernameResultLiveData = MutableLiveData<AuthResult<CheckUserNameResult>>()
    val checkUsernameResultLiveData: LiveData<AuthResult<CheckUserNameResult>> = _checkUsernameResultLiveData

    private val _tokenResponseLiveData = MutableLiveData<AuthResult<TokenResponse>>()
    val tokenResponseLiveData: LiveData<AuthResult<TokenResponse>> = _tokenResponseLiveData

    fun checkUserName(username:String){
        _checkUsernameResultLiveData.loadData {
            checkUserNameUseCase.execute(username)
        }
    }

    fun authorize(username: String, password: String,  checkUserNameResult: CheckUserNameResult){
        when(checkUserNameResult){
            CheckUserNameResult.Taken -> login(username, password)
            CheckUserNameResult.Free -> register(username, password)
            else -> {}
        }
    }

    fun saveToken(token:TokenResponse){
        viewModelScope.launch {
            saveTokenUseCase.execute(token)
        }
    }

    private fun login(username: String, password: String){
        _tokenResponseLiveData.loadData {
            authorizeUseCase.execute(username, password)
        }
    }

    private fun register(username: String, password: String){
        _tokenResponseLiveData.loadData {
            registerUseCase.execute(username, password)
        }
    }
}