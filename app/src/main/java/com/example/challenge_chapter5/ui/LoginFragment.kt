package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var  sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var username = sharedPref.getString("username", null)
        var password = sharedPref.getString("password", null)

        binding.login.setOnClickListener{
            var _username = binding.username.text.toString()
            var _password = binding.password.text.toString()
            if(username == _username && password == _password){
                var addData = sharedPref.edit()
                addData.putString("_email", _username)
                addData.putString("_password", _password)
                addData.apply()
                findNavController().navigate(R.id.action_loginFragment_to_filmFragment)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}