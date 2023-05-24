package com.example.homework5.presentation.images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val args by navArgs<ImagesFragmentArgs>()
    private val viewModel by viewModels<ImagesGalleryViewModel>()
    @Inject lateinit var imagesAdapter : ImagesGalleryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileId = args.profileId

        with(binding){
            with(viewModel){
                profileId?.let {
                    getProfileImages(it)
                }

                imagesLiveData.observe(viewLifecycleOwner){pagingData ->
                    imagesAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }

            }
            imagesGalleryRV.adapter = imagesAdapter
            imagesGalleryRV.layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}