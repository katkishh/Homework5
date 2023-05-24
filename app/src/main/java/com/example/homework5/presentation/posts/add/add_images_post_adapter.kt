package com.example.homework5.presentation.posts.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.data.model.Image
import com.example.homework5.data.model.ImageCompact
import com.example.homework5.databinding.ItemAddImageBinding
import javax.inject.Inject

class AddImagesPostAdapter @Inject constructor() : ListAdapter<ImageCompact, AddImagesPostAdapter.AddImagesPostViewHolder>(
    diffUtil
){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageCompact>(){
            override fun areItemsTheSame(oldItem: ImageCompact, newItem: ImageCompact): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageCompact, newItem: ImageCompact): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onCancelClick: (ImageCompact) -> Unit
    fun setCallback(callback: (ImageCompact) -> Unit){
        onCancelClick = callback
    }

    inner class AddImagesPostViewHolder(
        private val binding: ItemAddImageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ImageCompact){
            with(binding){
                imageViewCancelImage.setOnClickListener {
                    val imagesList: MutableList<ImageCompact> = currentList.toMutableList()
                    imagesList.remove(item)
                    submitList(imagesList)
                    onCancelClick
                }
                imageViewImage.load(item.uri)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddImagesPostViewHolder {
        val binding = ItemAddImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AddImagesPostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddImagesPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}