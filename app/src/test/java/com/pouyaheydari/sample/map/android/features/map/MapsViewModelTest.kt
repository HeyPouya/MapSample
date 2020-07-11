package com.pouyaheydari.sample.map.android.features.map

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

class MapsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MapsViewModel
    private lateinit var repository: DataRepositoryInterface
    private val vehicleList = getVehiclesList()

    @Before
    fun setUp() {
        repository = FakeDataRepository()
        viewModel = MapsViewModel(repository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testGettingDataOnViewModelInitialization() {
        runBlockingTest { repository.insertVehicle(vehicleList) }
        viewModel.getVehicleLiveData().getOrAwaitValue()

        assertThat(viewModel.getVehicleLiveData().value?.vehicles, `is`(getVehiclesList()))
    }
}