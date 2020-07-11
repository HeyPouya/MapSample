package com.pouyaheydari.sample.map.android.repository.local

import com.pouyaheydari.sample.map.android.room.VehicleDao

/**
 * All database Dao's will be gathered here
 *
 * @property vehicleDao
 */
class LocalRepository(private val vehicleDao: VehicleDao) : VehicleDao by vehicleDao