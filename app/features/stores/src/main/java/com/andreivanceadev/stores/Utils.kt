package com.andreivanceadev.stores

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager

@SuppressLint("MissingPermission")
fun getLocationCoordinates(context: Context): Pair<Float, Float> {
    val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers: List<String> = manager.getProviders(true)
    for (provider in providers) {
        val location = manager.getLastKnownLocation(provider)
        if (location != null) return location.latitude.toFloat() to location.longitude.toFloat()
    }
    return 0f to 0f
}