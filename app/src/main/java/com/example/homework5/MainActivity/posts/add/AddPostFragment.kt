package com.example.homework5.MainActivity.posts.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.MainActivity.models.ImagesModel
import com.example.homework5.R
import com.example.homework5.databinding.FragmentAddPostBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPostFragment: Fragment(R.layout.fragment_add_post) {
    private val binding by viewBinding(FragmentAddPostBinding::bind)

    //private var imagesList = MutableList<ImagesModel>()

    @Inject lateinit var addAdapter: AddImagesPostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }


        }

    }
}