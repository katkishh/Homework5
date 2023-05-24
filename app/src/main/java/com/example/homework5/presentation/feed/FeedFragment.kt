package com.example.homework5.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.R
import com.example.homework5.databinding.FragmentFeedBinding
import com.example.homework5.presentation.posts.PostImagesAdapter
import com.example.homework5.presentation.posts.PostsAdapter
import javax.inject.Inject

class FeedFragment: Fragment(R.layout.fragment_feed) {
    private val viewModel by viewModels<FeedViewModel>()
    private val binding by viewBinding(FragmentFeedBinding::bind)
    @Inject lateinit var postAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            with(viewModel){
                getFeed()
                postsLiveData.observe(viewLifecycleOwner){
                    postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            }
            feedRV.adapter = postAdapter

            postAdapter.setCallback {post ->
                findNavController().navigate(
                    FeedFragmentDirections.actionFeedFragmentToPostFragment(post.id)
                )
            }

            fabAddPost.setOnClickListener {
                FeedFragmentDirections.actionFeedFragmentToAddPostFragment()
            }

        }


    }
}