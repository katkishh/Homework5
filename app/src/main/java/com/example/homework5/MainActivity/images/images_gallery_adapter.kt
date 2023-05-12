package com.example.homework5.MainActivity.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.MainActivity.models.ImagesModel
import com.example.homework5.databinding.ItemImageBinding
import javax.inject.Inject

class ImagesGalleryAdapter @Inject constructor(): ListAdapter<ImagesModel, ImagesGalleryAdapter.ViewHolder>(diffUt){

    inner class ViewHolder(
        private val binding: ItemImageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ImagesModel){
            with(binding){
                imageViewImage.load(item.image)
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
        holder.bind(getItem(position))
    }

}

val diffUt = object : DiffUtil.ItemCallback<ImagesModel>(){
    override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
        return oldItem == newItem
    }

}