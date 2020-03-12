package com.example.testcicil.model.response

import com.google.gson.annotations.SerializedName

data class SearchItem(

	@field:SerializedName("Type")
	val Type: String? = null,

	@field:SerializedName("Year")
	val Year: String? = null,

	@field:SerializedName("imdbID")
	val imdbID: String? = null,

	@field:SerializedName("Poster")
	val Poster: String? = null,

	@field:SerializedName("Title")
	val Title: String? = null
)