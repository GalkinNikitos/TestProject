package com.example.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class Chat(
    val senderName: String,
    val profileImage: String,
    val lastMessage: String,
    val time: String
)

class ChatAdapter(private val chatList: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
        val senderNameTextView: TextView = itemView.findViewById(R.id.senderNameTextView)
        val lastMessageTextView: TextView = itemView.findViewById(R.id.lastMessageTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        Glide.with(holder.profileImageView.context).load(chat.profileImage).into(holder.profileImageView)
        holder.senderNameTextView.text = chat.senderName
        holder.lastMessageTextView.text = chat.lastMessage
        holder.timeTextView.text = chat.time
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}