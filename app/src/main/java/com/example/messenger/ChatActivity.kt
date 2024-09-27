//package com.example.messenger
//
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class ChatActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.chat_activity)
//        Log.d("ChatActivity", "onCreate")
//
//        val chatList = listOf(
//            Chat("Ronaldo", "https://thelawofattraction.ru/wp-content/uploads/8/e/d/8edf94662bf99ba1e65b51c2fe7c92e2.jpeg", "Гулять пойдешь?)", "10:30 AM"),
//            Chat("Messi", "https://wallpapers.com/images/hd/grin-messi-4k-ultra-hd-n1pg258bah0kbkma.jpg", "Когда тусить?", "11:15 AM"),
//            Chat("Ibragimovich", "https://almode.ru/uploads/posts/2020-11/1604429237_16-p-zlatan-ibragimovich-21.jpg", "Сегодня не приеду на треню", "12:00 PM"),
//            Chat("Ronaldo", "https://thelawofattraction.ru/wp-content/uploads/8/e/d/8edf94662bf99ba1e65b51c2fe7c92e2.jpeg", "Девчонки будут?", "13:30 AM"),
//            Chat("Messi", "https://wallpapers.com/images/hd/grin-messi-4k-ultra-hd-n1pg258bah0kbkma.jpg", "Барса выиграет?", "14:15 AM"),
//            Chat("Ibragimovich", "https://almode.ru/uploads/posts/2020-11/1604429237_16-p-zlatan-ibragimovich-21.jpg", "Как там Леха?", "18:00 PM")
//        )
//
//        val chatRecyclerView: RecyclerView = findViewById(R.id.chatRecyclerView)
//        chatRecyclerView.layoutManager = LinearLayoutManager(this)
//        chatRecyclerView.adapter = ChatAdapter(chatList)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        Log.d("ChatActivity", "onCreateView")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("ChatActivity", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("ChatActivity", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("ChatActivity", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("ChatActivity", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("ChatActivity", "onDestroy")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("ChatActivity", "onSaveInstanceState")
//    }
//
//}