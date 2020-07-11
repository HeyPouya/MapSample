package com.pouyaheydari.sample.map.android.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pouyaheydari.sample.map.android.base.BaseViewModel
import com.pouyaheydari.sample.map.android.pojo.ViewNavigationEnum
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import com.pouyaheydari.sample.map.android.utils.coroutinesExceptionHandler
import kotlinx.coroutines.launch

/**
 * Splash page ViewModel
 *
 * @property dataRepository
 */
class SplashViewModel(private val dataRepository: DataRepositoryInterface) : BaseViewModel() {

    private val liveData = MutableLiveData<ViewNavigationEnum>()

    /**
     * Decides which view should be presented to the user based on the network connection and if there is any data available on the device
     *
     * @param networkAvailable
     */
    fun decideNextView(networkAvailable: Boolean) =
        viewModelScope.launch(coroutinesExceptionHandler(exceptionLiveData)) {
            liveData.postValue(
                when {
                    networkAvailable -> ViewNavigationEnum.MAP
                    dataRepository.getOfflineVehicles()
                        .isNotEmpty() -> ViewNavigationEnum.VEHICLE_LIST
                    else -> ViewNavigationEnum.OFFLINE_NO_DATA
                }
            )
        }

    /**
     * Turns MutableLiveData to LiveData to prevent accidental post value
     *
     * @return LiveData of type [ViewNavigationEnum]
     */
    fun getNextPageLiveData(): LiveData<ViewNavigationEnum> = liveData
}