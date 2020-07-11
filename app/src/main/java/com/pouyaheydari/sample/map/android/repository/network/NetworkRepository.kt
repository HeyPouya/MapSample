package com.pouyaheydari.sample.map.android.repository.network

import com.pouyaheydari.sample.map.android.retrofit.RetrofitInterface

/**
 * All Retrofit interfaces will be gathered here
 *
 * @property retrofitInterface
 */
class NetworkRepository(private val retrofitInterface: RetrofitInterface) :
    RetrofitInterface by retrofitInterface