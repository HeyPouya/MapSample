package com.pouyaheydari.sample.map.android.features.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.sample.map.android.base.BaseViewModel
import com.pouyaheydari.sample.map.android.pojo.VehiclesData
import com.pouyaheydari.sample.map.android.repository.DataRepository
import kotlinx.coroutines.launch

class MapsViewModel(private val repository: DataRepository) : BaseViewModel() {
    private val liveData = MutableLiveData<VehiclesData>()

    init {
        viewModelScope.launch {
            val vehicles = repository.getVehicles()
            liveData.postValue(vehicles)
        }
    }

    fun getVehicleLiveData(): LiveData<VehiclesData> = liveData
}