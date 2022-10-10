package com.example.challenge_chapter5.ui

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_chapter5.R
import com.example.challenge_chapter5.databinding.FragmentFilmBinding
import com.example.challenge_chapter5.model.Item
import com.google.android.material.snackbar.Snackbar
import java.util.*

class FilmFragment : Fragment(), MovieAdapter.ListMovieInterface {
    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private  val viewModel : MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.home -> {
                    findNavController().navigate(R.id.filmFragment)
                    true
                }
                R.id.account -> {
                    findNavController().navigate(R.id.accountFragment)
                    true
                }
                R.id.Indonesia -> {
                    setLocale("id")
                    true
                }
                R.id.English -> {
                    setLocale("en")
                    true
                }
                else -> false
            }

            binding.drawerLayout.close()
            true
        }

        val adapter: MovieAdapter by lazy {
            MovieAdapter {

            }
        }

        binding.apply {
            viewModel.showItemListData()
            viewModel.getLiveDataMovie().observe(viewLifecycleOwner){
                if ( it != null){
                    adapter.setData(it.items as List<Item>)
                }else{
                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(
                            ContextCompat.getColor(requireContext(),
                            R.color.colorPrimary
                        ))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                        .show()
                }
            }
            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvPost.adapter = adapter

        }
    }
    override fun onItemClick(MovieDetail: Item) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        findNavController().navigate(R.id.filmFragment)
    }

}
