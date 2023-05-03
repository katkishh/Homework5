package com.example.homework5.ImagesActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.homework5.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.ImagesListModel
import com.example.homework5.databinding.FragmentImagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment: Fragment(R.layout.fragment_images) {

    companion object{
        private const val images = "images"

      //  fun createIntent(context: Context, list: ImagesListModel) =
      //      Intent(context, ImagesActivity::class.java).apply {
      //          putExtra(images, list)
      //      }
    }

    private val imagesAdapter by lazy { ImagesGalleryAdapter() }
    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val args by navArgs<ImagesFra>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.activityImagesRV){
            adapter = imagesAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        }

        val imagesList = navArgs<>()

            //intent.getParcelableExtra<ImagesListModel>(images)
        imagesAdapter.submitList(imagesList?.list)

        binding.toolbar.setNavigationOnClickListener {

        }
    }
}