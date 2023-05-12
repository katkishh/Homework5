package com.example.homework5.MainActivity.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.MainActivity.models.PostsModel
import com.example.homework5.databinding.ViewPostCardBinding
import javax.inject.Inject

class PostsAdapter @Inject constructor(): ListAdapter<PostsModel, PostsAdapter.ViewHolder>(diffUtil){

    var onClick: (PostsModel) -> Unit = {}

    inner class ViewHolder(
        private val binding: ViewPostCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(post: PostsModel){
            with(binding){
                textViewUserName.text = post.userName
                textViewPublicationDate.text = post.postDate
                textViewPostDescription.text = post.postDescription
                imageViewPostImage.load(post.postIMG)
                root.setOnClickListener {
                    onClick.invoke(post)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPostCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

val diffUtil = object : DiffUtil.ItemCallback<PostsModel>(){
    override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
        return oldItem == newItem
    }

}

