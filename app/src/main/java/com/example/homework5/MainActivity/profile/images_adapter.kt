package com.example.homework5.MainActivity.profile

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import coil.load
import com.example.homework5.MainActivity.models.ImagesListModel
import com.example.homework5.databinding.ViewImagesCardBinding
import javax.inject.Inject

class ImagesAdapter @Inject constructor(): RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>(){

    var onClick: (ImagesListModel) -> Unit = {}

    private var imagesData : ImagesListModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ViewImagesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        imagesData?.let { data ->
            holder.bind(data)
        }
    }

    inner class ImagesViewHolder(
        private val binding: ViewImagesCardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(imageList: ImagesListModel){
            with(binding){
                imageViewFirstImage.load(imageList.list.last().image)
                imageViewSecondImage.load(imageList.list[imageList.listSize - 2].image)
                imageViewThirdImage.load(imageList.list[imageList.listSize - 3].image)
                imageViewLastImage.load(imageList.list[imageList.listSize - 4].image)
                root.setOnClickListener {
                    onClick.invoke(imageList)
                }
            }
        }
    }

    fun submitImages(img: ImagesListModel){
        this.imagesData = img
        notifyDataSetChanged()
    }

}