package com.pouyaheydari.sample.map.android.pojo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VehiclesData(val vehicles: List<Vehicle>)

@JsonClass(generateAdapter = true)
data class Vehicle(
    val bearing: Float,
    val image_url: String,
    val lat: Double,
    val lng: Double,
    val type: String
)