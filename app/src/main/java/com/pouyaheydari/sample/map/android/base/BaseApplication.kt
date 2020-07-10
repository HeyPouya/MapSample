package com.pouyaheydari.sample.map.android.base

import android.app.Application
import com.pouyaheydari.sample.map.android.base.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * BaseApplication class to provide app level dependencies.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                viewModelModule,
                retrofitModule,
                moshiModule,
                dataRepositoryModule,
                moshiConverterFactoryModule,
                roomModule
            )
        }
    }
}
