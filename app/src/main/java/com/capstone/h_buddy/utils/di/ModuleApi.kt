package com.capstone.h_buddy.utils.di

import android.content.Context
import androidx.room.Room
import com.capstone.h_buddy.data.database.ArticlesDatabase
import com.capstone.h_buddy.data.database.local.ArticlesDao
import com.capstone.h_buddy.data.server.ArticlesApiService
import com.capstone.h_buddy.utils.BASE_URL
import com.capstone.h_buddy.utils.TIMEOUT_TIME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApi {
    @Provides
    @Singleton
    fun provideUrl() = BASE_URL

    @Provides
    @Singleton
    fun providerGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideTimeOut() = TIMEOUT_TIME

    @Provides
    @Singleton
    fun provideClient(time: Long) = OkHttpClient.Builder()
        .connectTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(base_url: String, gson: Gson, client: OkHttpClient): ArticlesApiService =
        Retrofit
            .Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client).build().create(ArticlesApiService::class.java)

    //Article Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArticlesDatabase {
        return Room.databaseBuilder(
            context,
            ArticlesDatabase::class.java,
            "articles_database"
        ).build()
    }

    @Provides
    fun provideArticlesDao(database: ArticlesDatabase) : ArticlesDao {
        return database.articlesDao()
    }
}