package com.capstone.h_buddy.data.server

import com.capstone.h_buddy.data.api.article.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {
    @GET("top-headlines")
    suspend fun getArticle(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ArticleResponse>
}