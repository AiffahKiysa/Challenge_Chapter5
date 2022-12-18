package com.example.challenge_chapter5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.data.user.DataUserManager
import com.example.challenge_chapter5.databinding.FragmentAccountBinding
import com.example.challenge_chapter5.ui.viewmodel.UpdateViewModel

class AccountFragment : Fragment() {
    lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: UpdateViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UpdateViewModel::class.java]
        binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser().observe(viewLifecycleOwner){
            binding.username.text = it.toString()
        }
        viewModel.getName().observe(viewLifecycleOwner){
            binding.name.text = it.toString()
        }
        viewModel.getEmail().observe(viewLifecycleOwner){
            binding.email.text = it.toString()
        }

        binding.btnEdit.setOnClickListener(){
            findNavController().navigate(R.id.action_accountFragment_to_updateFragment)
        }

        binding.cvHome.setOnClickListener(){
            findNavController().navigate(R.id.filmFragment)
        }

        binding.cvLogout.setOnClickListener(){
            viewModel.setIsLogin(false)
            Toast.makeText(context, "Berhasil logout", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }

    }


}