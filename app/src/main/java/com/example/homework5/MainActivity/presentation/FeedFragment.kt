package com.example.homework5.MainActivity.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.MainActivity.data.remote.model.FeedViewModel
import com.example.homework5.MainActivity.posts.PostsAdapter
import com.example.homework5.R
import com.example.homework5.databinding.FragmentFeedBinding
import javax.inject.Inject

class FeedFragment: Fragment(R.layout.fragment_feed) {
    private val viewModel by viewModels<FeedViewModel>()
    private val binding by viewBinding(FragmentFeedBinding::bind)
    @Inject lateinit var postAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postsLiveData.observe(viewLifecycleOwner){
            postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}