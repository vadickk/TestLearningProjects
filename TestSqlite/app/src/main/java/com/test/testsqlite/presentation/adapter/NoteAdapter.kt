package com.test.testsqlite.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testsqlite.R
import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.UserHolder>() {
    private var listOfUsers = listOf<Note>()

    class UserHolder(item: View) : RecyclerView.ViewHolder(item){
        private val binding = ItemNoteBinding.bind(item)
        fun bind(user: Note) = with(binding) {
            binding.titleItem.text = user.title
            binding.subtitleItem.text = user.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(listOfUsers[position])
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewContent(list: List<Note>) {
        listOfUsers = list
        notifyDataSetChanged()
    }

}