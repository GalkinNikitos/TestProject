//package com.example.messenger
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import com.example.messenger.databinding.ActivityMainBinding
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d("MainActivity", "onCreate")
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        //
//        val user = intent.getParcelableExtra<User>("user")
//        if (user != null) {
//            binding.emailname.setText(user.email)
//            binding.password.setText(user.password)
//        } else {
//            val email = intent.getStringExtra("email") ?: "nikson@mail.ru"
//            val password = intent.getStringExtra("password") ?: "1111111"
//            binding.emailname.setText(email)
//            binding.password.setText(password)
//        }
//        //
////        val email = intent.getStringExtra("email") ?: "nikson@mail.ru"
////        val password = intent.getStringExtra("password") ?: "1111111"
////        binding.emailname.setText(email)
////        binding.password.setText(password)
//        val domens: List<String> = listOf("@gmail.com", "@mail.ru", "@yandex.ru")
//        binding.loginButton.setOnClickListener(View.OnClickListener {
//            val hasValidSuffix = domens.any { suffix ->
//                binding.emailname.text.toString().endsWith(suffix)
//            }
//            if (hasValidSuffix && binding.password.text.toString().length >= 6){
//                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@MainActivity, ChatActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        binding.backToSignUpButton.setOnClickListener{
//            onBackPressed()
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("MainActivity", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("MainActivity", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("MainActivity", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("MainActivity", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("MainActivity", "onDestroy")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("MainActivity", "onSaveInstanceState")
//    }
//}