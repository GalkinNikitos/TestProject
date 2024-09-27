package com.example.messenger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ChatFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ChatFragment", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.chat_activity, container, false)
        Log.d("ChatFragment", "onCreateView")

        val chatList = listOf(
            Chat("Ronaldo", "https://thelawofattraction.ru/wp-content/uploads/8/e/d/8edf94662bf99ba1e65b51c2fe7c92e2.jpeg", "Гулять пойдешь?)", "10:30 AM"),
            Chat("Messi", "https://wallpapers.com/images/hd/grin-messi-4k-ultra-hd-n1pg258bah0kbkma.jpg", "Когда тусить?", "11:15 AM"),
            Chat("Ibragimovich", "https://almode.ru/uploads/posts/2020-11/1604429237_16-p-zlatan-ibragimovich-21.jpg", "Сегодня не приеду на треню", "12:00 PM"),
            Chat("Ronaldo", "https://thelawofattraction.ru/wp-content/uploads/8/e/d/8edf94662bf99ba1e65b51c2fe7c92e2.jpeg", "Девчонки будут?", "13:30 AM"),
            Chat("Messi", "https://wallpapers.com/images/hd/grin-messi-4k-ultra-hd-n1pg258bah0kbkma.jpg", "Барса выиграет?", "14:15 AM"),
            Chat("Ibragimovich", "https://almode.ru/uploads/posts/2020-11/1604429237_16-p-zlatan-ibragimovich-21.jpg", "Как там Леха?", "18:00 PM")
        )

        val chatRecyclerView: RecyclerView = view.findViewById(R.id.chatRecyclerView)
        chatRecyclerView.layoutManager = LinearLayoutManager(activity)
        chatRecyclerView.adapter = ChatAdapter(chatList)

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("ChatFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ChatFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ChatFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ChatFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ChatFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ChatFragment", "onDestroy")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("ChatFragment", "onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ChatFragment", "onSaveInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ChatFragment", "onViewCreated")
    }
}