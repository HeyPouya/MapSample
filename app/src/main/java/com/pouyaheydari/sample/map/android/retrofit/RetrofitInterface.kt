package com.pouyaheydari.sample.map.android.retrofit

import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import retrofit2.http.GET

/**
 * Retrofit main interface
 *
 */
interface RetrofitInterface {

    @GET("assets/test/document.json")
    suspend fun getVehicles(): VehiclesData
}