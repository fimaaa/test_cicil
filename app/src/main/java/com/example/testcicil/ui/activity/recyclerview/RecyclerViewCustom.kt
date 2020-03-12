package com.example.testcicil.ui.activity.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewCustom(private val listener: Listener): RecyclerView.OnScrollListener() {

    interface Listener{
        fun onBottomOf()
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val positionLoading = if(recyclerView.adapter?.itemCount?:1 > 1){
            (recyclerView.adapter?.itemCount?:1)- 1
        }else{
            0
        }
        println("positionloading = $positionLoading and item = ${layoutManager.findLastVisibleItemPosition()}")
        if (layoutManager.findLastVisibleItemPosition() == positionLoading) {
            listener.onBottomOf()
//            recyclerView.adapter?.setVisibilityProgress(true)
//            rcv_listnews_contenthome.removeOnScrollListener(this)
//            Run.after(2000L) {
//                loadNews()
//            }
        }
    }
}