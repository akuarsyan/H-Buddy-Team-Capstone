package com.capstone.h_buddy.data.api.article

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

)
@Entity(tableName = "articles")
data class ArticlesItem(

	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("herb")
	val herb: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	var isFavorite: Boolean = false
)
