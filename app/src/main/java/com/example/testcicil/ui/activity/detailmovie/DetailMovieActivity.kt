package com.example.testcicil.ui.activity.detailmovie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testcicil.R
import com.example.testcicil.base.BaseViewModel
import com.example.testcicil.databinding.ActivityDetailMovieBinding

class DetailMovieActivity():AppCompatActivity() {
    companion object{
        const val INTENT_ID_DETAILMOVIE = "intent_ID_DetailMovie"

        fun startActivity(mContext: Context, idMovies:String):Intent{
            val intent = Intent(mContext,DetailMovieActivity::class.java)
            intent.putExtra(INTENT_ID_DETAILMOVIE,idMovies)
            return intent
        }
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        viewModel =
            ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
//        ViewModelProvider(this).get(PostListViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.finishActivit.observe(this, Observer {
            if(it) finish()
        })
        viewModel.loadDetailMovies(intent.getStringExtra(INTENT_ID_DETAILMOVIE)?:"")
    }

}