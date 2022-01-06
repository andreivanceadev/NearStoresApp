package com.andreivanceadev.stores.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreModel(
    val name: String,
    val address: String,
    val postalCode: String,
    val city: String
) : Parcelable