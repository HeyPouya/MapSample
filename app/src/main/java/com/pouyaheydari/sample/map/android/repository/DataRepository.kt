package com.pouyaheydari.sample.map.android.repository

import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import com.pouyaheydari.sample.map.android.repository.network.NetworkRepository
import com.pouyaheydari.sample.map.android.retrofit.RetrofitInterface

class DataRepository(private val networkRepository: NetworkRepository) : RetrofitInterface {

    override suspend fun getVehicles(): VehiclesData = networkRepository.getVehicles()
}