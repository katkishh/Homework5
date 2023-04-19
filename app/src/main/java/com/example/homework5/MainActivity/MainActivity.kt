package com.example.homework5.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.*
import com.example.homework5.ImagesActivity.ImagesActivity
import com.example.homework5.PostsActivity.PostsActivity
import com.example.homework5.databinding.ActivityMainBinding
import com.example.homework5.databinding.ViewImagesCardBinding
import com.example.homework5.databinding.ViewPostCardBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingImage: ViewImagesCardBinding
    private lateinit var bindingPost: ViewPostCardBinding

    private val profileAdapter by lazy { ProfileAdapter() }

    private val imagesAdapter by lazy{ ImagesAdapter() }

    private val postsAdapter by lazy { PostsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingImage = ViewImagesCardBinding.inflate(layoutInflater)
        bindingPost = ViewPostCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val concatAdapter = ConcatAdapter(profileAdapter, imagesAdapter, postsAdapter)
        binding.recyclerView.adapter = concatAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

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

        val postsList = mutableListOf<PostsModel>().apply {
            add(
                PostsModel(
                    userName = "Evolitist",
                    postDate = "Apr 1, 2022 20:00:04",
                    postDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                    postIMG = images.last().image
                )
            )
        }

        profileAdapter.setUser(UserBioModel("Evolitist", "just do it.", 12, 5, 16))
        postsAdapter.submitList(postsList)
        postsAdapter.onClick = { post ->
            startActivity(PostsActivity.createIntent(this, post))
        }


        val imagesList = ImagesListModel(list = images, listSize = images.size)
        imagesAdapter.submitImages(imagesList)
        imagesAdapter.onClick = {list ->
            startActivity(ImagesActivity.createIntent(this, list))
        }
    }
}