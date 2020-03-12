package com.example.testcicil.ui.activity.searchmovie

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.testcicil.base.BaseViewModel
import com.example.testcicil.model.response.ResponseSearch
import com.example.testcicil.network.OmdApi
import com.example.testcicil.ui.adapter.searchmovie.SearchMovieAdapter
import com.example.testcicil.utils.Constans
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMovieViewModel(): BaseViewModel(){
    @Inject
    lateinit var postApi: OmdApi

    private lateinit var subscription: Disposable


    val searchText:MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val recyclerVisibility:MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<String> = MutableLiveData()
    val isScrolling:MutableLiveData<Boolean> = MutableLiveData()

    val listMovieAdapter:SearchMovieAdapter = SearchMovieAdapter()

    private var pagination = 1

    init {
        loadingVisibility.value = View.VISIBLE
        recyclerVisibility.value = View.GONE
        errorMessage.value = "No Data Inputed..."
        isScrolling.value = true
    }

    fun changeView(){
        if(errorMessage.value != null){
            loadingVisibility.value = View.VISIBLE
            recyclerVisibility.value = View.GONE
        }else{
            loadingVisibility.value = View.GONE
            recyclerVisibility.value = View.VISIBLE
        }
    }

    fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        println("tag onTextChanged $s")
        searchText.value = s.toString()
        loadListMovies(true)
    }

    fun loadListMovies(isNew:Boolean){
        if(::subscription.isInitialized)  onCleared()
        if(isNew) pagination = 1 else pagination++
        val text = searchText.value?:""
        subscription = postApi.getMovies(Constans.BASE_API_KEY,text,pagination)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrievePostListSuccess(result,isNew) },
                { err -> onRetrievePostListError(err.message?:"Something is Wrong",isNew) }
            )
    }

    private fun onRetrievePostListStart(){
        if(listMovieAdapter.itemCount <= 0) errorMessage.value = "Loading Data..."
    }

    private fun onRetrievePostListFinish(){
        isScrolling.value = (listMovieAdapter.isVisible == View.VISIBLE)
//        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(responseSearch:ResponseSearch,isNew: Boolean){
        if(responseSearch.Error == null) {
            println("isNew = $isNew onSuccess = $responseSearch")
            errorMessage.value = null
            listMovieAdapter.updateMovieList(responseSearch.Search,isNew)
        }else{
            onRetrievePostListError(responseSearch.Error,isNew)
        }
//        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(err:String,isNew: Boolean){
        println("isNew = $isNew  onError = $err")
        listMovieAdapter.updateMovieList(null,isNew)
        if(isNew) errorMessage.value = err
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}