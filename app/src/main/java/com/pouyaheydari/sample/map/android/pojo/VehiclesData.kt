package com.pouyaheydari.sample.map.android.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

/**
 * The main response we receive from the server
 *
 * @property vehicles
 */
@JsonClass(generateAdapter = true)
data class VehiclesData(val vehicles: List<Vehicle>)

/**
 * The Vehicle response we receive from the server and also DAO we store in the database
 *
 * @property uid we use this only for storing data
 * @property bearing
 * @property image_url
 * @property lat
 * @property lng
 * @property type
 */
@Entity
@JsonClass(generateAdapter = true)
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,
    val bearing: Float,
    val image_url: String,
    val lat: Double,
    val lng: Double,
    val type: String
)