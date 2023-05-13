package com.example.homework5.MainActivity.data.remote.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.homework5.MainActivity.domain.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel(
    val getProfileUseCase: GetProfileUseCase,
): ViewModel() {
    private val _postsLiveData = MutableLiveData<PagingData<Post>>()
    val postsLiveData: LiveData<PagingData<Post>> = _postsLiveData

    fun loadPosts(){
        viewModelScope.launch {
            getProfileUseCase.execute("evo")
                .cachedIn(viewModelScope)
                .collect{
                    _postsLiveData.value = it
                }
        }
    }
}