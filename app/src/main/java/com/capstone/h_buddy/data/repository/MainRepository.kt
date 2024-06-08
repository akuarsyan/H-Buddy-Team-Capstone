package com.capstone.h_buddy.data.repository

import com.capstone.h_buddy.data.api.article.ArticlesResponse
import com.capstone.h_buddy.data.server.ArticlesApiService
import com.capstone.h_buddy.utils.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ArticlesApiService) {
    suspend fun getArticles(): Flow<MyResponse<ArticlesResponse>> = flow {
        emit(MyResponse.loading())
        val response = api.getArticles()
        if (response.isSuccessful)
            emit(MyResponse.success(response.body()))
        else emit(MyResponse.error("Try again"))
    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }
}