package com.example.homework5.ImagesActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.ImagesListModel
import com.example.homework5.databinding.ActivityImagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesActivity: AppCompatActivity() {

    companion object{
        private const val images = "images"

        fun createIntent(context: Context, list: ImagesListModel) =
            Intent(context, ImagesActivity::class.java).apply {
                putExtra(images, list)
            }
    }

    private val imagesAdapter by lazy { ImagesGalleryAdapter() }

    private lateinit var binding: ActivityImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.activityImagesRV){
            adapter = imagesAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        }

        val imagesList = intent.getParcelableExtra<ImagesListModel>(images)
        imagesAdapter.submitList(imagesList?.list)

        binding.toolbar.setNavigationOnClickListener {

        }
    }
}