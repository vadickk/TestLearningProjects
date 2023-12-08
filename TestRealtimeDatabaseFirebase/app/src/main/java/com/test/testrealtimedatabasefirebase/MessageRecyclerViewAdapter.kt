package com.test.testrealtimedatabasefirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testrealtimedatabasefirebase.databinding.RvMessagesItemBinding

class MessageRecyclerViewAdapter : RecyclerView.Adapter<MessageRecyclerViewAdapter.MessageViewHolder>() {
    private var messages = listOf<Message>()

    class MessageViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RvMessagesItemBinding.bind(item)
        fun bind(message: Message) {
            binding.tvMessage.text = message.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_messages_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

    fun submitMessages(listOfMessages: List<Message>) {
        messages = listOfMessages
        notifyDataSetChanged()
    }
 }