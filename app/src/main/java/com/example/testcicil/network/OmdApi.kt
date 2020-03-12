package com.example.testcicil.network

import com.example.testcicil.model.response.ResponseDetailMovie
import com.example.testcicil.model.response.ResponseSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdApi {

    @GET("/")
    fun getMovies(
        @Query("apikey") searchKey:String,
        @Query("s") titleMovie:String,
        @Query("page") limitPage:Int
    ): Observable<ResponseSearch>


    @GET("/")
    fun getDetailMovies(
        @Query("apikey") searchKey:String,
        @Query("i") idMovie:String
    ):Observable<ResponseDetailMovie>
}