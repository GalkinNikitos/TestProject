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
import com.example.messenger.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment()  {

    private var _binding: SignUpFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SignUpFragment", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("SignUpFragment", "onCreateView")
        binding.signUpButton.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(binding.emailname.text.toString()).matches() &&
                binding.username.text.toString().length > 2 &&
                binding.password.text.toString().length >= 6)
            {
                Toast.makeText(activity, "Sign Up Successful!", Toast.LENGTH_SHORT).show()

                val email = binding.emailname.text.toString()
                val password = binding.password.text.toString()
                //
//                val user = User(email, password)
                //
//                val args = Bundle().apply {
//                    putString("email", email)
//                    putString("password", password)
//                }
                //
//                val args = Bundle().apply {
//                    putParcelable("user", user)
//                }
                //


                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(
                    email = email,
                    password = password,
                    user = User(email, password)
                ))

//                    findNavController().navigate(R.id.loginFragment, args)


            } else {
                Toast.makeText(activity, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.haveAccountButton.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        binding.backToHelloButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignUpFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignUpFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SignUpFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignUpFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SignUpFragment", "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUpFragment", "onDestroy")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("SignUpFragment", "onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("SignUpFragment", "onSaveInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SignUpFragment", "onViewCreated")
    }

}