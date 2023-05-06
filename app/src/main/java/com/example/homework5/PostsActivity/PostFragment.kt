package com.example.homework5.PostsActivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.homework5.R
import com.example.homework5.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment: Fragment(R.layout.fragment_post) {

    companion object{
        private const val POST_KEY = "post"

      //  fun createIntent(context: Context, thisPost: PostsModel) =
      //      Intent(context, PostsActivity::class.java).apply {
      //          putExtra(POST, thisPost)
      //      }
    }

    private val binding by viewBinding(FragmentPostBinding::bind)
    private val args by navArgs<PostFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.post
        //val post = intent.getParcelableExtra(POST, PostsModel::class.java)
        with(binding){
            textViewUserName.text = post.userName
            textViewPublicationDate.text = post.postDate
            textViewPostDescription.text = post.postDescription
            imageViewPostImage.load(post.postIMG)
        }
    }

}