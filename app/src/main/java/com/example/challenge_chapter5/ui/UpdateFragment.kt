package com.example.challenge_chapter5.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.data.user.DataUserManager
import com.example.challenge_chapter5.databinding.FragmentRegisterBinding
import com.example.challenge_chapter5.databinding.FragmentUpdateBinding
import com.example.challenge_chapter5.model.User
import com.example.challenge_chapter5.ui.viewmodel.LoginViewModel
import com.example.challenge_chapter5.ui.viewmodel.UpdateViewModel

class UpdateFragment : DialogFragment() {
    lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel
    private lateinit var pref: DataUserManager

    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        dialog!!.window
            ?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UpdateViewModel::class.java]
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var username = ""
//        Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
        var name = ""
        var email = ""
        var password = ""


        binding.apply {
            viewModel.getUser().observe(viewLifecycleOwner){
                dataEdit?.username = it.toString()
            }
            viewModel.getName().observe(viewLifecycleOwner){
                dataEdit?.name = it.toString()
            }
            viewModel.getEmail().observe(viewLifecycleOwner){
                dataEdit?.email = it.toString()
            }
            viewModel.getPassword().observe(viewLifecycleOwner){
                dataEdit?.password = it.toString()
            }
            dataEdit = User(username, name, email, password)
            binding.btnEdit.setOnClickListener(){
                username = binding.edtUsername.text.toString()
                name = binding.edtName.text.toString()
                email = binding.edtEmail.text.toString()
                password = dataEdit?.password.toString()

                viewModel.saveUser(username, name, email, password)
                Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_accountFragment)
            }

        }
    }
}