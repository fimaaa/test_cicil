package com.example.testcicil.model.response

import com.google.gson.annotations.SerializedName

data class RatingsItem(

	@field:SerializedName("Value")
	val Value: String? = null,

	@field:SerializedName("Source")
	val Source: String? = null
)