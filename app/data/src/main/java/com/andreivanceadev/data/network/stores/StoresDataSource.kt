package com.andreivanceadev.data.network.stores

import com.andreivanceadev.data.network.entities.StoresEntity
import javax.inject.Inject

class StoresDataSource @Inject constructor(
    private val storesAPI: StoresAPI
) {

    suspend fun getStores(lat: Float, lon: Float): Pair<List<StoresEntity>?, String?> {
        val response = storesAPI.getStores(lat, lon)
        return response.body()?.stores to response.errorBody()?.string()
    }

}