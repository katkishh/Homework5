package com.example.homework5.PostsActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.homework5.PostsModel
import com.example.homework5.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity: AppCompatActivity() {

    companion object{
        private const val POST = "post"

        fun createIntent(context: Context, thisPost: PostsModel) =
            Intent(context, PostsActivity::class.java).apply {
                putExtra(POST, thisPost)
            }
    }

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<PostsModel>(POST)
        //val post = intent.getParcelableExtra(POST, PostsModel::class.java)
        with(binding){
            textViewUserName.text = post?.userName
            textViewPublicationDate.text = post?.postDate
            textViewPostDescription.text = post?.postDescription
            imageViewPostImage.load(post?.postIMG)
        }
    }
}