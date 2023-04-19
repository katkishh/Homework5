package com.example.homework5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.databinding.ViewProfileCardBinding

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    private var thisUser: UserBioModel? = null

    inner class ViewHolder(
        private val binding: ViewProfileCardBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserBioModel){
            with(binding){
                textViewUserName.text = user.userName
                textViewUserStatus.text = user.userStatus
                textViewIMGNum.text = user.userIMGNum.toString()
                textViewSubsNum.text = user.userSubscribersNum.toString()
                textViewPostsNum.text = user.userPostsNum.toString()
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

    fun setUser(user: UserBioModel){
        this.thisUser = user
    }

}