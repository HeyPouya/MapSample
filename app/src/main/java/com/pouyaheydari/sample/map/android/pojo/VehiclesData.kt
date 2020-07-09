package com.pouyaheydari.sample.map.android.pojo

data class VehiclesData(val vehicles: List<Vehicle>)

data class Vehicle(
    val bearing: Int,
    val image_url: String,
    val lat: Double,
    val lng: Double,
    val type: String
)