package com.pouyaheydari.sample.map.android.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import com.pouyaheydari.sample.map.android.pojo.ViewNavigationEnum
import kotlinx.coroutines.launch

class SplashViewModel(private val dataRepository: DataRepositoryInterface) : ViewModel() {

    private val liveData = MutableLiveData<ViewNavigationEnum>()

    fun decideNextView(networkAvailable: Boolean) = viewModelScope.launch {
        liveData.postValue(
            when {
                networkAvailable -> ViewNavigationEnum.MAP
                dataRepository.getOfflineVehicles().isNotEmpty() -> ViewNavigationEnum.VEHICLE_LIST
                else -> ViewNavigationEnum.OFFLINE_NO_DATA
            }
        )
    }

    fun getNextPageLiveData(): LiveData<ViewNavigationEnum> = liveData
}