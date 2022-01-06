package com.andreivanceadev.data.network.entities

import com.google.gson.annotations.SerializedName

data class StoresEntity(

    @SerializedName("name")
    val name: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("postalCode")
    val postalCode: String,

    @SerializedName("city")
    val city: String
)