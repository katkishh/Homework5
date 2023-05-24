package com.example.homework5.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework5.data.model.Image
import com.example.homework5.domain.images.GetProfileImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesGalleryViewModel @Inject constructor(
    private val getProfileImagesUseCase: GetProfileImagesUseCase
): ViewModel(){
    private val _imagesLiveData = MutableLiveData<PagingData<Image>>()
    val imagesLiveData: LiveData<PagingData<Image>> = _imagesLiveData

    fun getProfileImages(profileId: String){
        viewModelScope.launch {
            getProfileImagesUseCase.execute(profileId)
                .cachedIn(viewModelScope)
                .collect{
                    _imagesLiveData.postValue(it)
                }
        }
    }


}