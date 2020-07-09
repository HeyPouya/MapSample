package com.pouyaheydari.sample.map.android.base.di

import com.pouyaheydari.sample.map.android.repository.DataRepository
import com.pouyaheydari.sample.map.android.repository.network.NetworkRepository
import com.pouyaheydari.sample.map.android.retrofit.RetrofitInterface
import org.koin.dsl.module

/**
 * Represents module of koin that the scopes inside it creates instance of [DataRepository] & [NetworkRepository].
 * this module injects [NetworkRepository] in [DataRepository].
 */
val dataRepositoryModule = module {
    single {
        DataRepository(get())
    }

    single {
        NetworkRepository(get())
    }
}
