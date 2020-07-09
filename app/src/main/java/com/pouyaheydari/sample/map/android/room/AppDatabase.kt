package com.pouyaheydari.sample.map.android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pouyaheydari.sample.map.android.pojo.Vehicle

@Database(entities = [Vehicle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
