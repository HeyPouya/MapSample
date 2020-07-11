package com.pouyaheydari.sample.map.android.repository.network

import com.pouyaheydari.sample.map.android.retrofit.RetrofitInterface

class NetworkRepository(private val retrofitInterface: RetrofitInterface) :
    RetrofitInterface by retrofitInterface