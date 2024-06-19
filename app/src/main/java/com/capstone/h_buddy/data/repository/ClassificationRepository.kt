package com.capstone.h_buddy.data.repository

import com.capstone.h_buddy.data.api.detectionmodel.ClassificationResponse
import com.capstone.h_buddy.data.server.ClassificationApiService
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassificationRepository @Inject constructor(
    private val classificationService: ClassificationApiService
) {
    suspend fun uploadImage(image: MultipartBody.Part): ClassificationResponse {
        return classificationService.uploadImage(image)
    }
}