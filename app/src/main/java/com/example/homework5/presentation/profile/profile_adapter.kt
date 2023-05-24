package com.example.homework5.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework5.data.model.Profile
import com.example.homework5.databinding.ViewProfileCardBinding
import javax.inject.Inject

class ProfileAdapter @Inject constructor(): RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    private var thisUser: Profile? = null

    fun setUser(user: Profile){
        thisUser = user
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ViewProfileCardBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: Profile?){
            with(binding){
                user?.let {
                    textViewUserName.text = it.userName
                    textViewUserStatus.text = it.bio
                    textViewIMGNum.text = it.imagesCount.toString()
                    textViewSubsNum.text = it.subscribersCount.toString()
                    textViewPostsNum.text = it.postsCount.toString()
                    imageViewAvatar.load(it.avatarLarge)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewProfileCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        thisUser?.let {data ->
            holder.bind(data)
        }

    }
}