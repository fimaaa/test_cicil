package com.example.testcicil.ui.activity.searchmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testcicil.R
import com.example.testcicil.databinding.ActivitySearchMovieBinding
import com.example.testcicil.ui.activity.recyclerview.RecyclerViewCustom

class SearchMovieActivity: AppCompatActivity(),RecyclerViewCustom.Listener {
    private lateinit var binding: ActivitySearchMovieBinding
    private lateinit var viewModel: SearchMovieViewModel
    val scroll = RecyclerViewCustom(this)


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_movie)

        viewModel =
            ViewModelProviders.of(this).get(SearchMovieViewModel::class.java)

//        ViewModelProvider(this).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            viewModel.changeView()
        })

        binding.viewModel = viewModel
        viewModel.isScrolling.observe(this, Observer {
            if(it){
                binding.rcvSearchMovie.addOnScrollListener(scroll)
            }else{
                binding.rcvSearchMovie.removeOnScrollListener(scroll)
            }
        })

    }

    override fun onBottomOf() {
        viewModel.loadListMovies(false)
    }
}