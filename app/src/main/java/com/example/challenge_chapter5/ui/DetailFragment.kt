package com.example.challenge_chapter5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        var getid = arguments?.getInt("id")
        var getoriginalTitle = arguments?.getString("originalTitle")
        var gettitle = arguments?.getString("title")
        var getposterPath = arguments?.getString("posterPath")
        var getrelease = arguments?.getString("release_date")
        var getoverview = arguments?.getString("overview")
        binding.ivPoster.load("https://www.themoviedb.org/t/p/w220_and_h330_face/" + getposterPath){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_menu_24)
        }

        binding.id.setText(getid.toString())
        binding.originalTitle.setText(getoriginalTitle)
        binding.title.setText(gettitle)
        binding.releaseDate.setText(getrelease)
        binding.overview.setText(getoverview)

        return binding.root
    }

}