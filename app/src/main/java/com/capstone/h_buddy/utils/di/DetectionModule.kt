package com.capstone.h_buddy.utils.di

import com.capstone.h_buddy.data.server.ClassificationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetectionModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://flask-tensorflow-gcolab-jvtjfxo25q-et.a.run.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideDetectionApiService(retrofit: Retrofit): ClassificationApiService {
        return retrofit.create(ClassificationApiService::class.java)
    }
}