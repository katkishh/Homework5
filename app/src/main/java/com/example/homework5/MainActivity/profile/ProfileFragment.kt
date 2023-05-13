package com.example.homework5.MainActivity.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.*
import com.example.homework5.MainActivity.data.remote.model.ProfileViewModel
import com.example.homework5.MainActivity.models.ImagesListModel
import com.example.homework5.MainActivity.models.ImagesModel
import com.example.homework5.MainActivity.models.PostsModel
import com.example.homework5.MainActivity.models.UserBioModel
import com.example.homework5.MainActivity.posts.PostsAdapter
import com.example.homework5.databinding.FragmentProfileBinding
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

        val concatAdapter = ConcatAdapter(profileAdapter, imagesAdapter, postsAdapter)
        binding.recyclerView.adapter = concatAdapter

        val images = mutableListOf(
            ImagesModel(image = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1526336024174-e58f5cdd8e13?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1523224042829-4731dd15a3bb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1447875569765-2b3db822bec9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1558350315-8aa00e8e4590?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1527525443983-6e60c75fff46?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=385&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1532675432006-329c6fed7045?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=327&q=80"),
            ImagesModel(image = "https://images.unsplash.com/photo-1508020963102-c6c723be5764?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80")
        )

        val postsList = MutableList(1) {
            PostsModel(
                userName = "Evolitist",
                postDate = "Apr 1, 2022 20:00:04",
                postDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                postIMG = images.last().image,
            )
        }

        profileAdapter.setUser(UserBioModel("Evolitist", "just do it.", 12, 5, 16))
        postsAdapter.submitList(postsList)
        postsAdapter.onClick = { post ->
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToPostFragment(post)
            )
        }


        val imagesList = ImagesListModel(list = images, listSize = images.size)
        imagesAdapter.submitImages(imagesList)
        imagesAdapter.onClick = {list ->
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToImagesFragment(list)
            )
        }

        with(viewModel){
            getProfile()
            profileLiveData.observe(viewLifecycleOwner){

            }
        }

    }
}