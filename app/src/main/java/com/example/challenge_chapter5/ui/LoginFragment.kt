package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.data.user.DataUserManager
import com.example.challenge_chapter5.databinding.FragmentLoginBinding
import com.example.challenge_chapter5.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekLogin()
        var username = ""
        var password = ""

        binding.login.setOnClickListener{
            viewModel.getUser().observe(viewLifecycleOwner){
                username = it.toString()
            }
            viewModel.getPassword().observe(viewLifecycleOwner){
                password = it.toString()
            }
            val _username = binding.username.text.toString()
            val _password = binding.password.text.toString()

            if (username == _username && password == _password){
                viewModel.setIsLogin(true)
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

    private fun cekLogin() {
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_loginFragment_to_filmFragment)
            },1000)
        }
    }

}