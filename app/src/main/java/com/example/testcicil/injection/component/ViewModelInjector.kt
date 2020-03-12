package com.example.testcicil.injection.component

import com.example.testcicil.injection.module.NetworkModule
import com.example.testcicil.ui.activity.detailmovie.DetailMovieViewModel
import com.example.testcicil.ui.activity.searchmovie.SearchMovieViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(searchMovieViewModel: SearchMovieViewModel)
    fun inject(detailMovieViewModel: DetailMovieViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}