package com.capstone.h_buddy.data.repository

import com.capstone.h_buddy.data.api.article.ArticleResponse
import com.capstone.h_buddy.data.server.ArticleApiService
import com.capstone.h_buddy.utils.API_TOKEN
import com.capstone.h_buddy.utils.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ArticleApiService) {
    suspend fun lastArticle(): Flow<MyResponse<ArticleResponse>> = flow {
        emit(MyResponse.loading())
        val response = api.getArticle("us", API_TOKEN)
        if (response.isSuccessful)
            emit(MyResponse.success(response.body()))
        else emit(MyResponse.error("Try again"))
    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }

}