//package com.example.messenger
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import com.example.messenger.databinding.SignUpActivityBinding
//
//class SignUpActivity : AppCompatActivity() {
//
//    private lateinit var binding: SignUpActivityBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.sign_up_activity)
//        Log.d("SignUpActivity", "onCreate")
//        binding = SignUpActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val domens: List<String> = listOf("@gmail.com", "@mail.ru", "@yandex.ru")
//        binding.signUpButton.setOnClickListener(View.OnClickListener {
//            //
//            val email = binding.emailname.text.toString()
//            val password = binding.password.text.toString()
//            //
//
//            val hasValidSuffix = domens.any { suffix ->
//                binding.emailname.text.toString().endsWith(suffix)
//            }
//            if (binding.username.text.toString().length > 2 && hasValidSuffix && binding.password.text.toString().length >= 6){
//                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
//                //
//                val user = User(email, password)
//                //
//                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
////                intent.putExtra("email", binding.emailname.text.toString())
////                intent.putExtra("password", binding.password.text.toString())
//                //
//                intent.putExtra("user", user)
//                //
//                startActivity(intent)
//
//            } else {
//                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        binding.haveAccountButton.setOnClickListener{
//            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.backToHelloButton.setOnClickListener{
//            onBackPressed()
//        }
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("SignUpActivity", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("SignUpActivity", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("SignUpActivity", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("SignUpActivity", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("SignUpActivity", "onDestroy")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("SignUpActivity", "onSaveInstanceState")
//    }
//}