package com.main.testskeletonloading.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.main.testskeletonloading.R
import com.main.testskeletonloading.databinding.ItemUserBinding
import com.main.testskeletonloading.model.User

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    var users = mutableListOf<User>()

    class UsersViewHolder(view: View): ViewHolder(view) {
        private val binding by lazy { ItemUserBinding.bind(view) }
        fun bind(user: User) {
            binding.tvUsername.text = user.username
            binding.tvUserCompany.text = user.userCompany
            Glide.with(itemView).load(user.avatarUrl).into(binding.ivUserAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(listOfUsers: MutableList<User>) {
        users.clear()
        users = listOfUsers
        notifyDataSetChanged()
    }
}