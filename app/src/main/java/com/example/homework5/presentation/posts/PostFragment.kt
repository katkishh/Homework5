package com.example.homework5.presentation.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.homework5.R
import com.example.homework5.data.remote.LoadableResult
import com.example.homework5.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment: Fragment(R.layout.fragment_post) {

    private val binding by viewBinding(FragmentPostBinding::bind)
    private val args by navArgs<PostFragmentArgs>()
    private val viewModel by viewModels<PostViewModel>()
    @Inject lateinit var postImagesAdapter: PostImagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.post
        with(binding){
            postsImagesRV.adapter = postImagesAdapter
            with(viewModel){
                getPost(post)
                postLiveData.observe(viewLifecycleOwner){result ->
                    when(result){
                        is LoadableResult.Error -> {
                            Toast.makeText(requireContext(), R.string.error_network, Toast.LENGTH_SHORT).show()
                        }
                        is LoadableResult.Loading -> {}
                        is LoadableResult.Success -> {
                            result.value.also {post ->
                                textViewPostDescription.text = post.text
                                postImagesAdapter.submitList(post.images)
                                if (post.owner.avatarUrl != null){
                                    imageViewAvatar.load(post.owner.avatarUrl)
                                    textViewInitial.text = null
                                } else{
                                    post.owner.displayName?.let { username ->
                                        textViewInitial.text = username.first().toString()
                                    }
                                }
                                textViewUserName.text = post.owner.displayName
                                textViewPublicationDate.text = post.dataCreated.toString()
                            }
                        }
                    }
                }
            }

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

    }

}