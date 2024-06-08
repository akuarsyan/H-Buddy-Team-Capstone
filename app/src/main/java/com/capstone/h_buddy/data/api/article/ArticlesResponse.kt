package com.capstone.h_buddy.data.api.article

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

)

data class ArticlesItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("herb")
	val herb: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
