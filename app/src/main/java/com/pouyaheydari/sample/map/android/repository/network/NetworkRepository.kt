package com.pouyaheydari.sample.map.android.repository.network

import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import com.pouyaheydari.sample.map.android.retrofit.RetrofitInterface

class NetworkRepository(private val retrofitInterface: RetrofitInterface) : RetrofitInterface {

    override suspend fun getVehicles(): VehiclesData = retrofitInterface.getVehicles()

}