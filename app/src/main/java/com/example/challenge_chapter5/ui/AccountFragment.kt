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
        var getname = sharedPrefs.getString("name", null)
        var getemail = sharedPrefs.getString("email", null)

        binding.name.setText(getusername)
        binding.username.setText(getname)
        binding.email.setText(getemail)

        binding.cvHome.setOnClickListener(){
            findNavController().navigate(R.id.filmFragment)
        }

    }


}