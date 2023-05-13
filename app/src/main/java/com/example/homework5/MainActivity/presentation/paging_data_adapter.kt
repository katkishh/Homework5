package com.example.homework5.MainActivity.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.MainActivity.models.ImagesListModel
import com.example.homework5.databinding.ViewImagesCardBinding

class PagingDataAdapter: RecyclerView.Adapter<PagingDataAdapter.ViewHolder>(){

    var onClick: (ImagesListModel) -> Unit = {}

    private var imagesData : ImagesListModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingDataAdapter.ViewHolder {
        val binding = ViewImagesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: PagingDataAdapter.ViewHolder, position: Int) {
        imagesData?.let { data ->
            holder.bind(data)
        }
    }

    inner class ViewHolder(
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