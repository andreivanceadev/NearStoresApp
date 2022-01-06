package com.andreivanceadev.stores.model.mappers

import com.andreivanceadev.data.network.entities.StoresEntity
import com.andreivanceadev.stores.model.StoreModel

fun StoresEntity.toModel() = StoreModel(
    name = this.name,
    address = this.address,
    postalCode = this.postalCode,
    city = this.city,
)