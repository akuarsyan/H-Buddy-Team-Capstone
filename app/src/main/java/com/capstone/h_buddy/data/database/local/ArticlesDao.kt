package com.capstone.h_buddy.data.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.capstone.h_buddy.data.api.article.ArticlesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<ArticlesItem>>

    @Update
    suspend fun updateArticle(article: ArticlesItem)

    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE id = :articleId")
    suspend fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean)

}