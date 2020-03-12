package com.example.testcicil.model.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovie(

	@field:SerializedName("Metascore")
	val Metascore: String? = null,

	@field:SerializedName("BoxOffice")
	val BoxOffice: String? = null,

	@field:SerializedName("Website")
	val Website: String? = null,

	@field:SerializedName("imdbRating")
	val imdbRating: String? = null,

	@field:SerializedName("imdbVotes")
	val imdbVotes: String? = null,

	@field:SerializedName("Ratings")
	val Ratings: List<RatingsItem?>? = null,

	@field:SerializedName("Runtime")
	val Runtime: String? = null,

	@field:SerializedName("Language")
	val Language: String? = null,

	@field:SerializedName("Rated")
	val Rated: String? = null,

	@field:SerializedName("Production")
	val Production: String? = null,

	@field:SerializedName("Released")
	val Released: String? = null,

	@field:SerializedName("imdbID")
	val imdbID: String? = null,

	@field:SerializedName("Plot")
	val Plot: String? = null,

	@field:SerializedName("Director")
	val Director: String? = null,

	@field:SerializedName("Title")
	val Title: String? = null,

	@field:SerializedName("Actors")
	val Actors: String? = null,

	@field:SerializedName("Response")
	val Response: String? = null,

	@field:SerializedName("Error")
	val Error: String? = null,

	@field:SerializedName("Type")
	val Type: String? = null,

	@field:SerializedName("Awards")
	val Awards: String? = null,

	@field:SerializedName("DVD")
	val DVD: String? = null,

	@field:SerializedName("Year")
	val Year: String? = null,

	@field:SerializedName("Poster")
	val Poster: String? = null,

	@field:SerializedName("Country")
	val Country: String? = null,

	@field:SerializedName("Genre")
	val Genre: String? = null,

	@field:SerializedName("Writer")
	val Writer: String? = null
)