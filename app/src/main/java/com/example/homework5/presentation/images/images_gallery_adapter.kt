package com.example.homework5.presentation.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.data.model.Image
import com.example.homework5.databinding.ItemImageBinding
import javax.inject.Inject

class ImagesGalleryAdapter @Inject constructor(): PagingDataAdapter<Image, ImagesGalleryAdapter.ViewHolder>(diffUt){

    inner class ViewHolder(
        private val binding: ItemImageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Image){
            with(binding){
                imageViewImage.load(item.sizes.first().url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesGalleryAdapter.ViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesGalleryAdapter.ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}

val diffUt = object : DiffUtil.ItemCallback<Image>(){
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}