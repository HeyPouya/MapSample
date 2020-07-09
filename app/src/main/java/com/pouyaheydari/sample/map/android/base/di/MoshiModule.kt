package com.pouyaheydari.sample.map.android.base.di

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Represents instance of MoshiConverterFactory.
 */
val moshiConverterFactoryModule = module {
    single {
        /**
         * Provides MoshiConverterFactory.
         */
        MoshiConverterFactory.create(get())
    }
}

/**
 * Represents builder of Moshi.
 */
val moshiModule = module {
    single {
        /**
         * Provides MoshiConverterFactory.
         */
        Moshi.Builder().build()
    }
}
