package com.example.testcicil.ui.adapter.searchmovie

import androidx.lifecycle.MutableLiveData
import com.example.testcicil.base.BaseViewModel
import com.example.testcicil.model.response.SearchItem

class SearchMovieAdapterViewModel: BaseViewModel() {

    val movieTitle = MutableLiveData<String>()
    val movieType = MutableLiveData<String>()
    val movieYear = MutableLiveData<String>()
    val movieID = MutableLiveData<String>()
    val movieImage = MutableLiveData<String>()

    fun bind(itemMovie: SearchItem?){
        movieTitle.value = itemMovie?.Title
        movieType.value = itemMovie?.Type
        movieYear.value = itemMovie?.Year
        movieID.value = itemMovie?.imdbID
        movieImage.value = itemMovie?.Poster
    }

}