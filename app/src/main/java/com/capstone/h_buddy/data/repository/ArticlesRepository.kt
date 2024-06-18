package com.capstone.h_buddy.data.repository

import androidx.lifecycle.LiveData
import com.capstone.h_buddy.data.api.article.ArticlesItem
import com.capstone.h_buddy.data.database.local.ArticlesDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(private val articlesDao: ArticlesDao) {

    val allArticles: LiveData<List<ArticlesItem>> = articlesDao.getAllArticles()

    suspend fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean) {
        articlesDao.updateFavoriteStatus(articleId, isFavorite)
    }



}