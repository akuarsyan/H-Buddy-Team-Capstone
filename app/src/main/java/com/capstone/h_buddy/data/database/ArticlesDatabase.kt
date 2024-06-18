package com.capstone.h_buddy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.h_buddy.data.api.article.ArticlesItem
import com.capstone.h_buddy.data.database.local.ArticlesDao

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}