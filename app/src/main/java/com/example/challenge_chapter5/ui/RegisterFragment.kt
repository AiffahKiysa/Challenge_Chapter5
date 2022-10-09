package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var  sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        binding.register.setOnClickListener{
            registerBtn()
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
    fun registerBtn (){
        val username = binding.txtUsername.text.toString()
        val fullname = binding.txtFullname.text.toString()
        val password = binding.txtPassword.text.toString()
        val confirmPassword = binding.txtConfirmPassword.text.toString()

        if (password == confirmPassword){
            var addData = sharedPref.edit()
            addData.putString("username", username)
            addData.putString("fullname", fullname)
            addData.putString("password", password)
            addData.apply()
            Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        else
            Toast.makeText(requireContext(), "Password Not Match", Toast.LENGTH_SHORT).show()
    }
}