package com.capstone.h_buddy.data.api.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse (
    val articles: List<Article>?= null
)

data class Article(
    var title: String,
    var description: String,
    var url: String,
    var publishedAt: String,
    @SerializedName("urlToImage")
    var Imageurl: String,
)