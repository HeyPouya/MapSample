package com.pouyaheydari.sample.map.android.repository

import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import com.pouyaheydari.sample.map.android.repository.network.NetworkRepository

class DataRepository(private val networkRepository: NetworkRepository) {

    suspend fun getVehicles(): VehiclesData = networkRepository.getVehicles()
}