package com.andreivanceadev.stores.model

import com.andreivanceadev.data.network.stores.StoresDataSource
import com.andreivanceadev.stores.model.mappers.toModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoresRepository @Inject constructor(
    private val storesDataSource: StoresDataSource
) {

    suspend fun getStores(lat: Float, lon: Float): Pair<List<StoreModel>?, String?> {
        val response = storesDataSource.getStores(lat, lon)
        return response.first?.map { it.toModel() } to response.second
    }

}