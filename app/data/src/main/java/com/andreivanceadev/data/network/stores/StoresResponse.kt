package com.andreivanceadev.data.network.stores

import com.andreivanceadev.data.network.entities.StoresEntity
import com.google.gson.annotations.SerializedName

data class StoresResponse(
    @SerializedName("stores")
    val stores: List<StoresEntity>
)