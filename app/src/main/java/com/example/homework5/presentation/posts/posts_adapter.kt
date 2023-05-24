package com.example.homework5.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.data.model.Post
import com.example.homework5.databinding.ViewPostCardBinding
import javax.inject.Inject

class PostsAdapter @Inject constructor(): PagingDataAdapter<Post, PostsAdapter.ViewHolder>(diffUtil){

    @Inject lateinit var postImagesAdapter: PostImagesAdapter
    private lateinit var onClick: (Post) -> Unit
    fun setCallback(callback: (Post) -> Unit){
        onClick = callback
    }

    inner class ViewHolder(
        private val binding: ViewPostCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            with(binding){
                if (post.owner.avatarUrl != null){
                    imageViewAvatar.load(post.owner.avatarUrl)
                    textViewInitial.text = null
                }else{
                    textViewInitial.text = post.owner.username.first().toString()
                }
                textViewUserName.text = post.owner.username

                textViewPublicationDate.text = post.dataCreated.toString()

                textViewPostDescription.text = post.text
                postImagesRV.adapter = postImagesAdapter
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
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}

val diffUtil = object : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}

