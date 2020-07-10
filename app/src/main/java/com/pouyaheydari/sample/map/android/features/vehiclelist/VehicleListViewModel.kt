package com.pouyaheydari.sample.map.android.features.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import kotlinx.coroutines.launch

class VehicleListViewModel(repository: DataRepositoryInterface) : ViewModel() {
    private val liveData = MutableLiveData<List<Vehicle>>()

    init {
        viewModelScope.launch {
            val data = repository.getOfflineVehicles()
            liveData.postValue(data)
        }
    }

    fun getVehicleListLiveData(): LiveData<List<Vehicle>> = liveData
}