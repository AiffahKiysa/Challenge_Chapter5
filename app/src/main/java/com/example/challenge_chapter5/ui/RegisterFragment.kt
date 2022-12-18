package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.data.user.DataUserManager
import com.example.challenge_chapter5.databinding.FragmentRegisterBinding
import com.example.challenge_chapter5.model.User
import com.example.challenge_chapter5.ui.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var pref: DataUserManager
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[RegisterViewModel::class.java]
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener{
            registerBtn()
        }
    }
    fun registerBtn (){
        val username = binding.txtUsername.text.toString()
        val name = binding.txtName.text.toString()
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        val confirmPassword = binding.txtConfirmPassword.text.toString()

        if (password == confirmPassword){
            viewModel.saveUser(username, name, email, password)
            Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        else
            Toast.makeText(requireContext(), "Password Not Match", Toast.LENGTH_SHORT).show()
    }
}