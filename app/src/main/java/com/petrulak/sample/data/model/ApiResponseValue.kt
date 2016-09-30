package com.petrulak.sample.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponseValue(
  @SerializedName("x") val dateAsLong: Long,
  @SerializedName("y") val price: Double
)

