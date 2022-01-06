package com.andreivanceadev.data.network.stores

import com.andreivanceadev.data.network.entities.StoresEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StoresAPI {

    @GET("stores/?radius=1000&page=1&pageSize=10&clientApplicationKey=testApplication")
    @Headers("accept: application/json")
    suspend fun getStores(@Query("latitude") lat: Float, @Query("longitude") long: Float): Response<StoresResponse>

}