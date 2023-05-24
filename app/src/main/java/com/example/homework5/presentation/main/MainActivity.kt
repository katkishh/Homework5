package com.example.homework5.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.homework5.R
import com.example.homework5.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ActivityMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.authFragment, R.id.addPostFragment, R.id.imagesFragment -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        viewModel.getToken()
        viewModel.tokenLiveData.observe(this) {
            val navGraph = if (it != null) {
                navController.navInflater.inflate(R.navigation.nav_graph)
            } else {
                navController.navInflater.inflate(R.navigation.auth_nav_graph)
            }
            navController.graph = navGraph
        }
    }
        
}
