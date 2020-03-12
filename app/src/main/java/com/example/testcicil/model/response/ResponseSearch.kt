package com.example.testcicil.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@SerializedName("Response")
	@Expose
	val Response: String? = null,

    @SerializedName("Error")
    @Expose
    val Error: String? = null,

	@SerializedName("totalResults")
	@Expose
	val totalResults: String? = null,

	@SerializedName("Search")
	@Expose
	val Search: MutableList<SearchItem?>? = null
)