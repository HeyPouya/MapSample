package com.pouyaheydari.sample.map.android.features.vehiclelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pouyaheydari.sample.map.android.MainCoroutineRule
import com.pouyaheydari.sample.map.android.features.FakeDataRepository
import com.pouyaheydari.sample.map.android.features.getVehiclesList
import com.pouyaheydari.sample.map.android.getOrAwaitValue
import com.pouyaheydari.sample.map.android.repository.DataRepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VehicleListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: VehicleListViewModel
    private lateinit var repository: DataRepositoryInterface
    private val vehicleList = getVehiclesList()

    @Before
    fun setUp() {
        repository = FakeDataRepository()
        viewModel = VehicleListViewModel(repository)
    }

    @Test
    fun getVehicleListData() {
        runBlockingTest { repository.insertVehicle(vehicleList) }
        viewModel.getVehicleListLiveData().getOrAwaitValue()

        assertThat(viewModel.getVehicleListLiveData().value, `is`(vehicleList))
    }

}