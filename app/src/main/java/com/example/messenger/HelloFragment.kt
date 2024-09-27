package com.example.messenger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HelloFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HelloFragment", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hello_activity, container, false)
        Log.d("HelloFragment", "onCreateView")

        val helloButton: AppCompatButton = view.findViewById(R.id.myButton)
        helloButton.setOnClickListener {

            findNavController().navigate(R.id.signUpFragment)

        }
        val viewSettingButton: AppCompatButton = view.findViewById(R.id.view_settings_button)
        viewSettingButton.setOnClickListener {
            findNavController().navigate(HelloFragmentDirections.actionHelloFragmentToSettingsFragment())
        }
        val viewSettings1Button: AppCompatButton = view.findViewById(R.id.view_settings_button_1)
        viewSettings1Button.setOnClickListener{
            findNavController().navigate(HelloFragmentDirections.actionHelloFragmentToSettingsFragment1())
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("HelloFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HelloFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HelloFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HelloFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HelloFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HelloFragment", "onDestroy")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("HelloFragment", "onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("HelloFragment", "onSaveInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HelloFragment", "onViewCreated")
    }

}