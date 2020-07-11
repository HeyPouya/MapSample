package com.pouyaheydari.sample.map.android.features.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.sample.map.android.base.BaseViewModel
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import com.pouyaheydari.sample.map.android.utils.coroutinesExceptionHandler
import kotlinx.coroutines.launch

class VehicleListViewModel(private val repository: DataRepositoryInterface) : BaseViewModel() {
    private val liveData = MutableLiveData<List<Vehicle>>()

    fun getVehicles() = viewModelScope.launch(coroutinesExceptionHandler(exceptionLiveData)) {
        val data = repository.getOfflineVehicles()
        liveData.postValue(data)
    }

    fun getVehicleListLiveData(): LiveData<List<Vehicle>> = liveData
}