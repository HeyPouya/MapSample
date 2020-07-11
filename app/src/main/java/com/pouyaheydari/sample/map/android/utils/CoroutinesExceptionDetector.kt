package com.pouyaheydari.sample.map.android.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler


/**
 * Used to Exception handler of coroutines for cases different status code of error.
 *
 * @param liveData The mutableLiveData of error response.
 */
fun coroutinesExceptionHandler(liveData: MutableLiveData<Any>) =
    CoroutineExceptionHandler { _, throwable ->
        liveData.postValue(throwable.message)
    }