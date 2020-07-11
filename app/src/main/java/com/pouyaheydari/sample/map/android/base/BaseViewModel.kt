package com.pouyaheydari.sample.map.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base viewModel to provide needed features in all Child class viewModels.
 */
open class BaseViewModel : ViewModel() {

    protected val exceptionLiveData = MutableLiveData<Any>()

    /**
     * Used to all viewModels for exception data response.
     *
     * @return The mutable live data of ExceptionResource class model.
     */
    fun getExceptionData(): LiveData<Any> = exceptionLiveData

}
