package com.example.homework5.presentation.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.*
import com.example.homework5.data.model.*
import com.example.homework5.data.remote.LoadableResult
import com.example.homework5.databinding.FragmentProfileBinding
import com.example.homework5.presentation.posts.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    @Inject lateinit var profileAdapter: ProfileAdapter

    @Inject lateinit var imagesAdapter: ImagesAdapter

    @Inject lateinit var postsAdapter: PostsAdapter

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userId: String? = null

        val concatAdapter = ConcatAdapter(profileAdapter, imagesAdapter, postsAdapter)
        with(binding){
            profileRV.adapter = concatAdapter

            with(viewModel){
                getUserId()

                userIdLiveData.observe(viewLifecycleOwner){
                    getProfile(it)
                }

                profileLiveData.observe(viewLifecycleOwner){result ->
                    when(result){
                        is LoadableResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                R.string.error_network,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is LoadableResult.Loading -> {}
                        is LoadableResult.Success -> {
                            profileAdapter.setUser(result.value)
                            imagesAdapter.submitImages(result.value.images)
                            getProfilePosts(result.value.id)
                            userId = result.value.id
                        }
                    }
                }
            }

            fabAddPost.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToAddPostFragment()
                )
            }
        }

        imagesAdapter.setCallback {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToImagesFragment(userId)
            )
        }

        postsAdapter.setCallback {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToPostFragment(it.id)
            )
        }
    }
}