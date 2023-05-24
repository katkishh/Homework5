package com.example.homework5.presentation.posts.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.R
import com.example.homework5.data.model.ImageCompact
import com.example.homework5.databinding.FragmentAddPostBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPostFragment: Fragment(R.layout.fragment_add_post) {
    private val binding by viewBinding(FragmentAddPostBinding::bind)
    private val viewModel by viewModels<AddPostViewModel>()

    @Inject lateinit var addAdapter: AddImagesPostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var uris: MutableList<ImageCompact>? = null

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(4)){ uriList ->
            if (uriList != null) {
                uris = uriList.map {
                    ImageCompact(uri = it)
                }.toMutableList()
                addAdapter.submitList(uris)
                uriList.map { viewModel.uploadImage(it) }
            }
        }
        with(binding){
            imagesPostRV.adapter = addAdapter
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            with(viewModel){
                imageLiveData.observe(viewLifecycleOwner){
                    uris?.let { imageCompactList ->
                        if (imageCompactList.size < 5){
                            buttonAddImage.visibility = View.VISIBLE
                        }
                        else{
                            buttonAddImage.visibility = View.GONE
                        }
                    }
                    if (uris == null) { buttonAddImage.visibility = View.VISIBLE }

                    buttonAddImage.setOnClickListener {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }

                }

                deleteImageLiveData.observe(viewLifecycleOwner){
                    addAdapter.setCallback { image ->
                        deleteImage(image.id)
                    }
                }
                var listByteArray =  mutableListOf<ByteArray>()
                uris?.map { imageCompact ->
                    convertUriToByteArray(imageCompact.uri)?.let {
                        listByteArray.add(it)
                    }
                }

                postLiveData.observe(viewLifecycleOwner){
                    toolbar.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.actionSubmit -> {
                                if (editTextPostText.text.toString().isNotBlank() || addAdapter.currentList.isNotEmpty()){
                                    createPost(
                                        editTextPostText.text.toString(),
                                        listByteArray
                                    )
                                    findNavController().popBackStack()

                                } else{
                                    Toast.makeText(
                                        requireContext(),
                                        R.string.empty_post,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                true
                            }
                            else -> false
                        }

                    }

                }
            }

        }

    }
}