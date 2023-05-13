package com.example.homework5.MainActivity.data.remote.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework5.MainActivity.data.remote.LoadableResult
import com.example.homework5.MainActivity.domain.GetProfileUseCase
import com.example.homework5.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
):BaseViewModel() {

    private val _profileLiveData = MutableLiveData<LoadableResult<Profile>>()
    val profileLiveData: LiveData<LoadableResult<Profile>> = _profileLiveData

    fun getProfile(profileId:String = "evo"){
        // viewModelScope.launch {
        //   getProfileUseCase.execute(profileId).also { profile ->
        // _profileLiveData.value = profile
        //     _profileLiveData.postValue(profile)     //спустя чуть-чуть времени запустит
        //}
        _profileLiveData.loadData{
            getProfileUseCase.execute(profileId)
        }
    }

}