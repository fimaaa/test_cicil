package com.example.testcicil.ui.adapter.searchmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testcicil.R
import com.example.testcicil.databinding.ItemLoadingBinding
import com.example.testcicil.databinding.ItemSearchMovieBinding
import com.example.testcicil.model.response.SearchItem
import com.example.testcicil.ui.activity.detailmovie.DetailMovieActivity
import com.example.testcicil.ui.adapter.other.LoadingAdapterViewModel
import kotlinx.android.synthetic.main.item_search_movie.view.*

class SearchMovieAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var movieList:MutableList<SearchItem?>? = null

    var isVisible = View.VISIBLE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingMovie: ItemSearchMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_movie, parent, false)
        val bindingLoading:ItemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_loading, parent, false)
        return when(viewType){
            1->{
                ViewHolderSearchMovie(
                    bindingMovie
                )
            }
            else->{
                ViewHolderLoading(
                    bindingLoading
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position >= itemCount-1){
            0
        }else{
            1
        }
    }

    override fun getItemCount(): Int {
        return  (movieList?.size?:0)+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position >= itemCount-1) {
            (holder as ViewHolderLoading).bind(isVisible)
        }else{
            (holder as ViewHolderSearchMovie).bind(movieList?.get(position))
        }
    }

    fun updateMovieList(movieList:MutableList<SearchItem?>?,isNew: Boolean){
        if(isNew) {
            isVisible = View.VISIBLE
            this.movieList = movieList
            notifyDataSetChanged()
        }else{
            if (movieList != null) {
                this.movieList?.addAll(movieList)
            }else{
                isVisible = View.GONE
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolderLoading(private val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root){
        private val viewModel = LoadingAdapterViewModel()

        fun bind(view:Int){
            viewModel.changeVisible(view)
            binding.viewModel = viewModel
        }
    }


    class ViewHolderSearchMovie(private val binding: ItemSearchMovieBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel =
            SearchMovieAdapterViewModel()

        fun bind(item:SearchItem?){
            viewModel.bind(item)
            binding.viewModel = viewModel
            binding.root.btn_detail_movie.setOnClickListener {
                val mContext = binding.root.context
                mContext.startActivity(DetailMovieActivity.startActivity(mContext,item?.imdbID?:""))
            }
        }
    }

}