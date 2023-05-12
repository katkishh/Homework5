package com.example.homework5.MainActivity.posts.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.MainActivity.models.ImagesModel
import com.example.homework5.databinding.ItemAddImageBinding
import javax.inject.Inject

class AddImagesPostAdapter @Inject constructor() : ListAdapter<ImagesModel, AddImagesPostAdapter.AddImagesPostViewHolder>(diffUtil){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImagesModel>(){
            override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onCancelClick: (ImagesModel) -> Unit = {}

    inner class AddImagesPostViewHolder(
        private val binding: ItemAddImageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ImagesModel){
            with(binding){
                imageViewCancelImage.setOnClickListener { onCancelClick(item) }
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

    //override fun getItemCount(): Int {
    //    TODO("Not yet implemented")
    //}

    override fun onBindViewHolder(holder: AddImagesPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}