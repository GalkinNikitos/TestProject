package com.example.messenger

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.messenger.databinding.LoginBinding

class LoginFragment : Fragment() {


    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!
    private val args : LoginFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LoginFragment", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = LoginBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("LoginFragment", "onCreateView")
        //
        //val user = arguments?.getParcelable<User>("user")
        //
//        val user = args.user
//        val email = arguments?.getString("email") ?: "nikson@mail.ru"
//        val password = arguments?.getString("password") ?: "1111111"

        //FIXME
        // val email = args.email
        // val password = args.password
        val email = args.user?.email ?: ""
        val password = args.user?.password ?: ""
        binding.emailname.setText(email)
        binding.password.setText(password)


        //
//        if (user != null) {
//            binding.emailname.setText(user.email)
//            binding.password.setText(user.password)
//        }
//        else{
//            val defaultEmail = "nikson@mail.ru"
//            val defaultPassword = "1111111"
//            binding.emailname.setText(defaultEmail)
//            binding.password.setText(defaultPassword)
//        }

        binding.loginButton.setOnClickListener {
            if  (Patterns.EMAIL_ADDRESS.matcher(binding.emailname.text.toString()).matches() &&
                binding.password.text.toString().length >= 6)
            {
                Toast.makeText(activity, "Login Successful!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.chatFragment)

            } else {
                    Toast.makeText(activity, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }

        binding.heroesButton.setOnClickListener{
            findNavController().navigate(R.id.mainActivityMain)
        }

        binding.backToSignUpButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LoginFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LoginFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LoginFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LoginFragment", "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginFragment", "onDestroy")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("LoginFragment", "onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("LoginFragment", "onSaveInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LoginFragment", "onViewCreated")
    }
}