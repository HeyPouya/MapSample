package com.pouyaheydari.sample.map.android.base.di

import androidx.room.Room
import com.pouyaheydari.sample.map.android.room.AppDatabase
import com.pouyaheydari.sample.map.android.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Provides Room instance
 */
val roomModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DATABASE_NAME).build()
    }

    single {
        val appDatabase: AppDatabase = get()
        appDatabase.vehicleDao()
    }
}
