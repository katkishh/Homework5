package com.example.homework5.MainActivity.images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework5.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.databinding.FragmentImagesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment: Fragment(R.layout.fragment_images) {

    @Inject lateinit var imagesAdapter : ImagesGalleryAdapter
    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val args by navArgs<ImagesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.activityImagesRV){
            adapter = imagesAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        }

        val imagesList = args.imageList

        imagesAdapter.submitList(imagesList?.list)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                ImagesFragmentDirections.actionImagesFragmentToProfileFragment()
            )
        }
    }
}