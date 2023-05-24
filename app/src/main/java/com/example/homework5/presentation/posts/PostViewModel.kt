package com.example.homework5.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework5.base.BaseViewModel
import com.example.homework5.data.model.Post
import com.example.homework5.data.remote.LoadableResult
import com.example.homework5.domain.posts.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
): BaseViewModel() {

    private val _postLiveData = MutableLiveData<LoadableResult<Post>>()
    val postLiveData: LiveData<LoadableResult<Post>> = _postLiveData

    fun getPost(postId: String){
        _postLiveData.loadData {
            getPostUseCase.execute(postId)
        }
    }
}