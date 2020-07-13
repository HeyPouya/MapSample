package com.pouyaheydari.sample.map.android.features

import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface

/**
 * A fake repository to be used in test
 *
 */
class FakeDataRepository : DataRepositoryInterface {

    private val vehiclesList: MutableList<Vehicle> = emptyList<Vehicle>().toMutableList()

    override suspend fun getVehicles(): VehiclesData = VehiclesData(vehiclesList)

    override suspend fun getOfflineVehicles(): List<Vehicle> = vehiclesList

    override suspend fun deleteAllVehicles() {}

    override suspend fun insertVehicle(vehicle: List<Vehicle>) {
        vehiclesList.addAll(vehicle)
    }
}