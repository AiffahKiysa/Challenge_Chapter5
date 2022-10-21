package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentAccountBinding
import com.example.challenge_chapter5.model.User

class AccountFragment : Fragment() {
    lateinit var binding: FragmentAccountBinding
    lateinit var  sharedPrefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        var getusername = sharedPrefs.getString("username", null)
        var getname = "Name : " + sharedPrefs.getString("name", null)
        var getemail = "Email : " + sharedPrefs.getString("email", null)

        binding.name.setText(getusername)
        binding.username.setText(getname)
        binding.email.setText(getemail)

        binding.btnEdit.setOnClickListener(){
            findNavController().navigate(R.id.action_accountFragment_to_updateFragment)
        }

        binding.cvHome.setOnClickListener(){
            findNavController().navigate(R.id.filmFragment)
        }

        binding.cvLogout.setOnClickListener(){
            sharedPrefs = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
            var addData = sharedPrefs.edit()
            addData.putString("_username", null)
            addData.putString("_password", null)
            addData.apply()
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }

    }


}