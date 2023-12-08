package com.test.firebase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.firebase.databinding.UserListItemBinding

class UserAdapter(val listener: Listener) : RecyclerView.Adapter<UserAdapter.OurViewHolder>() {
    private var listOfUsers = listOf<User>()

    class OurViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = UserListItemBinding.bind(item)
        fun bind(user: User, listener: Listener) = with(binding){
            userName.text = user.userName
            message.text = user.message
            binding.cardView.setOnClickListener {
                listener.onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return OurViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    override fun onBindViewHolder(holder: OurViewHolder, position: Int) {
        holder.bind(listOfUsers[position], listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<User>) {
        listOfUsers = list
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(user: User)
    }


}