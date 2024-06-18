package com.capstone.h_buddy.data.api.detectionmodel

import com.google.gson.annotations.SerializedName

data class ClassificationResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("confidenceScore")
	val confidenceScore: Any? = null,

	@field:SerializedName("isAboveThreshold")
	val isAboveThreshold: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	val status: String? = "contoh",
)
