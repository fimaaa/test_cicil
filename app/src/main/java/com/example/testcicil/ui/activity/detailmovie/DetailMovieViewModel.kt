package com.example.testcicil.ui.activity.detailmovie

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.testcicil.base.BaseViewModel
import com.example.testcicil.model.response.ResponseDetailMovie
import com.example.testcicil.network.OmdApi
import com.example.testcicil.utils.Constans
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailMovieViewModel():BaseViewModel() {
    @Inject
    lateinit var postApi: OmdApi

    private lateinit var subscription: Disposable

    val loadingVisibility:MutableLiveData<Int> = MutableLiveData()
    val contentVisibility:MutableLiveData<Int> = MutableLiveData()

    val imageBanner:MutableLiveData<String> = MutableLiveData()
    val titleMovie:MutableLiveData<String> = MutableLiveData()
    val infoMovie:MutableLiveData<String> = MutableLiveData()
    val yearMovie:MutableLiveData<String> = MutableLiveData()
    val ratingMovie:MutableLiveData<String> = MutableLiveData()
    val plotMovie:MutableLiveData<String> = MutableLiveData()

    val finishActivit:MutableLiveData<Boolean> = MutableLiveData()

    fun loadDetailMovies(idMovie:String){
        println("gget Movie with ID = $idMovie")
        if(::subscription.isInitialized)  onCleared()
        subscription = postApi.getDetailMovies(Constans.BASE_API_KEY,idMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrievePostListSuccess(result) },
                { err -> onRetrievePostListError(err.message?:"Something is Wrong") }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        contentVisibility.value = View.GONE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
        contentVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListSuccess(responseServer:ResponseDetailMovie){
        println("onSuccess = $responseServer")
        if(responseServer.Error == null){
            imageBanner.value = responseServer.Poster
            titleMovie.value = responseServer.Title
            infoMovie.value = "${responseServer.Genre} - ${responseServer.Runtime}"
            yearMovie.value = responseServer.Year
            ratingMovie.value = responseServer.imdbRating
            plotMovie.value = responseServer.Plot
        }else{
            onRetrievePostListError(responseServer.Error)
        }
    }

    private fun onRetrievePostListError(err:String){
        println("onError = $err")
        finishActivit.value = true
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}