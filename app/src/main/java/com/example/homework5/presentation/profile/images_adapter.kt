package com.example.homework5.presentation.profile

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import coil.load
import com.example.homework5.data.model.Image
import com.example.homework5.databinding.ViewImagesCardBinding
import javax.inject.Inject

class ImagesAdapter @Inject constructor(): RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>(){

    private var imagesData : List<Image>? = null
    private lateinit var onClick: () -> Unit

    fun setCallback(callback: () -> Unit){
        onClick = callback
    }
    fun submitImages(img: List<Image>){
        imagesData = img
        notifyDataSetChanged()
    }

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
        fun bind(imageList: List<Image>?){
            with(binding){
                imageList?.let {
                    imageViewFirstImage.load(getMinSizeUrl(it.last()))
                    imageViewSecondImage.load(getMinSizeUrl(it[it.lastIndex - 1]))
                    imageViewThirdImage.load(getMinSizeUrl(it[it.lastIndex - 2]))
                    imageViewLastImage.load(getMinSizeUrl(it[it.lastIndex - 3]))
                }
                root.setOnClickListener {
                    onClick
                }
            }
        }
    }

    private fun getMinSizeUrl(image: Image):String{
        return image.sizes.first().url
    }

}