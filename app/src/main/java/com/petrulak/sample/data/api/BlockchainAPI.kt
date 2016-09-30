package com.petrulak.sample.data.api


import com.petrulak.sample.data.model.ApiResponse

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


class BlockchainAPI(retrofit: Retrofit) {

  private val client: BlockchainAPIClient

  init {
    client = retrofit.create(BlockchainAPIClient::class.java)
  }

  fun getData(days: String): Observable<ApiResponse> {
    return client.getData(days)
  }

  internal interface BlockchainAPIClient {

    @GET("/charts/market-price?cors=true&format=json&lang=en")
    fun getData(@Query("timespan") days: String): Observable<ApiResponse>

  }
}
