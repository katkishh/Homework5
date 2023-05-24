package com.example.homework5.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.data.model.Image
import com.example.homework5.databinding.ItemImagePostBinding
import javax.inject.Inject

class PostImagesAdapter @Inject constructor(): ListAdapter<Image, PostImagesAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Image>(){
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

    }
) {

    inner class ViewHolder(
        private val binding: ItemImagePostBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Image){
            with(binding){
                imageViewImagePostItem.load(item.sizes.last().url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImagePostBinding.inflate(
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