package com.example.homework5.presentation.posts.add

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework5.data.remote.LoadableResult
import com.example.homework5.data.model.Post
import com.example.homework5.domain.images.AddImageUseCase
import com.example.homework5.domain.posts.CreatePostUseCase
import com.example.homework5.base.BaseViewModel
import com.example.homework5.data.model.Image
import com.example.homework5.domain.GetContentUriUseCase
import com.example.homework5.domain.images.DeleteImageUseCase
import com.example.homework5.domain.images.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val addImageUseCase: AddImageUseCase,
    private val getContentUriUseCase: GetContentUriUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
):BaseViewModel() {

    private val _imageLiveData = MutableLiveData<LoadableResult<Image>>()
    val imageLiveData: LiveData<LoadableResult<Image>> = _imageLiveData

    private val _deleteImageLiveData = MutableLiveData<LoadableResult<Boolean>>()
    val deleteImageLiveData: LiveData<LoadableResult<Boolean>> = _deleteImageLiveData

    private val _postLiveData = MutableLiveData<LoadableResult<Post>>()
    val postLiveData: LiveData<LoadableResult<Post>> = _postLiveData

    fun createPost(text: String?, images: List<ByteArray>?){
        _postLiveData.loadData {
            createPostUseCase.execute(text, images)
        }
    }

    fun uploadImage(image: Uri){
        getContentUriUseCase(image)?.let {
            _imageLiveData.loadData {
                addImageUseCase.execute(it)
            }
        }
    }

    fun deleteImage(imageId:String){
        _deleteImageLiveData.loadData {
            deleteImageUseCase.execute(imageId)
        }
    }

    fun convertUriToByteArray(uri: Uri): ByteArray?{
        return getContentUriUseCase.invoke(uri)
    }
}