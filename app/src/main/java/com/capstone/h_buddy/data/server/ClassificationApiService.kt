package com.capstone.h_buddy.data.server

import com.capstone.h_buddy.data.api.detectionmodel.ClassificationResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClassificationApiService {
    @Multipart
    @POST("/skin-cancer/predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): ClassificationResponse
}