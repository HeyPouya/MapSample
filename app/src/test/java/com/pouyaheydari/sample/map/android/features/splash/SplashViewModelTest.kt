package com.pouyaheydari.sample.map.android.features.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pouyaheydari.sample.map.android.MainCoroutineRule
import com.pouyaheydari.sample.map.android.features.FakeDataRepository
import com.pouyaheydari.sample.map.android.features.getVehiclesList
import com.pouyaheydari.sample.map.android.getOrAwaitValue
import com.pouyaheydari.sample.map.android.pojo.ViewNavigationEnum
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * A class to test [SplashViewModel]
 *
 */
class SplashViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SplashViewModel
    private lateinit var repository: DataRepositoryInterface
    private val vehicleList = getVehiclesList()

    @Before
    fun setUp() {
        repository = FakeDataRepository()
        viewModel = SplashViewModel(repository)
    }

    @Test
    fun decideNextView_networkAvailable_expectedMap() {
        val isNetworkAvailable = true

        viewModel.decideNextView(isNetworkAvailable)

        viewModel.getNextPageLiveData().getOrAwaitValue()
        assertThat(viewModel.getNextPageLiveData().value, `is`(ViewNavigationEnum.MAP))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun decideNextView_networkUnAvailable_expectedVehicleList() {
        val isNetworkAvailable = false
        runBlockingTest { repository.insertVehicle(vehicleList) }

        viewModel.decideNextView(isNetworkAvailable)

        viewModel.getNextPageLiveData().getOrAwaitValue()
        assertThat(viewModel.getNextPageLiveData().value, `is`(ViewNavigationEnum.VEHICLE_LIST))
    }

    @Test
    fun decideNextView_networkUnAvailable_expectedOfflineNoData() {
        val isNetworkAvailable = false

        viewModel.decideNextView(isNetworkAvailable)

        viewModel.getNextPageLiveData().getOrAwaitValue()
        assertThat(viewModel.getNextPageLiveData().value, `is`(ViewNavigationEnum.OFFLINE_NO_DATA))
    }
}