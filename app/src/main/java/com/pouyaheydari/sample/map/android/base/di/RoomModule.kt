package com.pouyaheydari.sample.map.android.base.di

import androidx.room.Room
import com.pouyaheydari.sample.map.android.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "vehicles").build()
    }

    single {
        val appDatabase: AppDatabase = get()
        appDatabase.vehicleDao()
    }
}
