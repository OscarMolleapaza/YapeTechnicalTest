@file:Suppress("DEPRECATION")

package com.omolleapaza.yapetechnicaltest.ui.extensions

import android.location.Location
import com.google.android.gms.maps.model.LatLng

fun Location.convertToLatLng(): LatLng{
    return LatLng(this.latitude, this.longitude)
}

fun LatLng.convertToLocation(): Location{
    val location = Location ("")
    location.longitude = this.longitude
    location.latitude = this.latitude
    return location
}

fun Location.createLocation(lt: Double, ln: Double): Location{
    this.longitude = ln
    this.latitude = lt
    return this
}