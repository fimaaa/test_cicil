package com.example.testcicil.ui.adapter.other

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.testcicil.base.BaseViewModel

class LoadingAdapterViewModel:BaseViewModel() {

    val movieTitle = MutableLiveData<Int>()

    fun changeVisible(view:Int){
        movieTitle.value = view
    }

}