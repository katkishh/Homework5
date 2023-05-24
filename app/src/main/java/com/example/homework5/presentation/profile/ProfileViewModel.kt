package com.example.homework5.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework5.data.remote.LoadableResult
import com.example.homework5.domain.GetProfileUseCase
import com.example.homework5.base.BaseViewModel
import com.example.homework5.data.model.Post
import com.example.homework5.data.model.Profile
import com.example.homework5.domain.posts.GetProfilePostsUseCase
import com.example.homework5.domain.token.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getProfilePostsUseCase: GetProfilePostsUseCase
):BaseViewModel() {

    private val _profileLiveData = MutableLiveData<LoadableResult<Profile>>()
    val profileLiveData: LiveData<LoadableResult<Profile>> = _profileLiveData

    private val _userIdLiveData = MutableLiveData<String?>()
    val userIdLiveData: LiveData<String?> = _userIdLiveData

    private val _postLiveData = MutableLiveData<PagingData<Post>>()
    val postLiveData: LiveData<PagingData<Post>> = _postLiveData

    fun getUserId(){
        viewModelScope.launch {
            _userIdLiveData.postValue(
                getUserIdUseCase.execute()
            )
        }
    }

    fun getProfile(profileId:String?){
        profileId?.let {
            _profileLiveData.loadData{
                getProfileUseCase.execute(it)
            }
        }
    }

    fun getProfilePosts(profileId: String){
        viewModelScope.launch {
            getProfilePostsUseCase.execute(profileId)
                .cachedIn(viewModelScope)
                .collect{
                    _postLiveData.postValue(it)
                }
        }
    }

}