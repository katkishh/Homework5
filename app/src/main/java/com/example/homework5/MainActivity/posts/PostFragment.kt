package com.example.homework5.MainActivity.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.homework5.R
import com.example.homework5.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment: Fragment(R.layout.fragment_post) {

    private val binding by viewBinding(FragmentPostBinding::bind)
    private val args by navArgs<PostFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.post
        with(binding){
            textViewUserName.text = post?.userName
            textViewPublicationDate.text = post?.postDate
            textViewPostDescription.text = post?.postDescription
            imageViewPostImage.load(post?.postIMG)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                PostFragmentDirections.actionPostFragmentToProfileFragment()
            )
        }
    }

}