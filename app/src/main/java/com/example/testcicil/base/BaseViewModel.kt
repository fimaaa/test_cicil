package com.example.testcicil.base

import androidx.lifecycle.ViewModel
import com.example.testcicil.injection.component.DaggerViewModelInjector
import com.example.testcicil.injection.component.ViewModelInjector
import com.example.testcicil.injection.module.NetworkModule
import com.example.testcicil.ui.activity.detailmovie.DetailMovieViewModel
import com.example.testcicil.ui.activity.searchmovie.SearchMovieViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SearchMovieViewModel -> injector.inject(this)
            is DetailMovieViewModel -> injector.inject(this)
        }
    }
}