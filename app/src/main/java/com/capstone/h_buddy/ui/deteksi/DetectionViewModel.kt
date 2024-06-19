package com.capstone.h_buddy.ui.deteksi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.h_buddy.data.api.detectionmodel.ClassificationResponse
import com.capstone.h_buddy.data.repository.ClassificationRepository
import com.capstone.h_buddy.data.server.ClassificationApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetectionViewModel @Inject constructor(
    private val repository: ClassificationRepository
): ViewModel() {
    private val _classificationResponse = MutableLiveData<ClassificationResponse>()
    val classificationResponse: LiveData<ClassificationResponse> get() = _classificationResponse

    fun uploadImage(image: MultipartBody.Part){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.uploadImage(image)
                Log.d("DetectionViewModel", "Response: $response")
                _classificationResponse.postValue(response)
            } catch (e: Exception){
                Log.d("DetectionViewModel", "Error: ${e.message}")
            }
        }
    }
}