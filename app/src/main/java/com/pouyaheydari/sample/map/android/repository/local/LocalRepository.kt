package com.pouyaheydari.sample.map.android.repository.local

import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.room.VehicleDao

class LocalRepository(private val vehicleDao: VehicleDao) : VehicleDao {

    override suspend fun getOfflineVehicles(): List<Vehicle> = vehicleDao.getOfflineVehicles()

    override suspend fun deleteAllVehicles() = vehicleDao.deleteAllVehicles()

    override suspend fun insertVehicle(vehicle: List<Vehicle>) = vehicleDao.insertVehicle(vehicle)
}