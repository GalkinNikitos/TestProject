//package com.example.messenger
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.AppCompatButton
//
//
//class HelloActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.hello_activity)
//        Log.d("HelloActivity", "onCreate")
//
//        val helloButton: AppCompatButton = findViewById(R.id.myButton)
//        helloButton.setOnClickListener {
//            val intent = Intent(this@HelloActivity, SignUpActivity::class.java)
//            startActivity(intent)
//        }
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("HelloActivity", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("HelloActivity", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("HelloActivity", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("HelloActivity", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("HelloActivity", "onDestroy")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("HelloActivity", "onSaveInstanceState")
//    }
//}