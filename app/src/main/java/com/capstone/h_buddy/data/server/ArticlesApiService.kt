package com.capstone.h_buddy.data.server

import com.capstone.h_buddy.data.api.article.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesApiService {
    @GET("articles")
    suspend fun getArticles(): Response<ArticlesResponse>
}