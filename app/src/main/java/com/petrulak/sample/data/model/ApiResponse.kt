package com.petrulak.sample.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
  val status: String,
  val name: String,
  val unit: String,
  val period: String,
  val description: String,
  @SerializedName("values") val listValues: List<ApiResponseValue>
)
