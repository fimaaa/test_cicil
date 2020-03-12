package com.example.testcicil.utils

import android.annotation.SuppressLint
import com.bumptech.glide.request.RequestOptions
import com.example.testcicil.R

class Constans {
    companion object {
        const val BASE_URL: String = "https://www.omdbapi.com"

        const val BASE_API_KEY = "5a6c96c0"

        @SuppressLint("CheckResult")
        fun getRequestOption(): RequestOptions {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.color.light_transparent)
            requestOptions.error(R.color.black)

            return requestOptions
        }
    }
}