package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentRegisterBinding
import com.example.challenge_chapter5.databinding.FragmentUpdateBinding
import com.example.challenge_chapter5.model.User

class UpdateFragment : DialogFragment() {
    lateinit var binding: FragmentUpdateBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var username = sharedPref.getString("username", null).toString()
        var name = sharedPref.getString("name", null).toString()
        var email = sharedPref.getString("email", null).toString()
        var password = sharedPref.getString("password", null).toString()

        binding.apply {
            dataEdit = User(username, name, email, password)
            binding.btnEdit.setOnClickListener(){
                username = binding.edtUsername.text.toString()
                name = binding.edtName.text.toString()
                email = binding.edtEmail.text.toString()

                var addData = sharedPref.edit()
                addData.putString("username", username)
                addData.putString("name", name)
                addData.putString("email", email)
                addData.apply()
                Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_accountFragment)
            }

        }
    }
}