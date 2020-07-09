package com.pouyaheydari.sample.map.android.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VehiclesData(val vehicles: List<Vehicle>)

@Entity
@JsonClass(generateAdapter = true)
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val bearing: Float,
    val image_url: String,
    val lat: Double,
    val lng: Double,
    val type: String
)