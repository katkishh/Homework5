package com.example.homework5.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework5.domain.token.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityMainViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
): ViewModel() {
    private val _tokenLiveData = MutableLiveData<String?>()
    val tokenLiveData: LiveData<String?> = _tokenLiveData

    fun getToken() {
        viewModelScope.launch {
            _tokenLiveData.postValue(
                getTokenUseCase.execute()
            )
        }
    }
}