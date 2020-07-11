package com.pouyaheydari.sample.map.android.repository.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pouyaheydari.sample.map.android.MainCoroutineAndroidRule
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.repository.getVehiclesList
import com.pouyaheydari.sample.map.android.room.AppDatabase
import com.pouyaheydari.sample.map.android.room.VehicleDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalRepositoryTest {

    private lateinit var vehicleDao: VehicleDao
    private lateinit var db: AppDatabase
    private lateinit var localRepository: LocalRepository
    private val vehicleList = getVehiclesList()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineAndroidRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        vehicleDao = db.vehicleDao()
        localRepository = LocalRepository(vehicleDao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getOfflineVehicles_noVehiclesAdded_expectedEmptyList() {
        var vehicles: List<Vehicle> = emptyList()
        runBlockingTest { vehicles = localRepository.getOfflineVehicles() }

        assertThat(vehicles, `is`(emptyList()))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getOfflineVehicles_vehiclesAdded_expectedNotEmptyList() {
        var vehicles: List<Vehicle> = emptyList()

        runBlockingTest { localRepository.insertVehicle(vehicleList) }
        runBlockingTest { vehicles = localRepository.getOfflineVehicles() }

        assertThat(vehicles, not(emptyList()))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteAllVehicles_vehiclesAdded_expectedEmptyList() {
        var vehicles: List<Vehicle> = emptyList()

        runBlockingTest { localRepository.insertVehicle(vehicleList) }
        runBlockingTest { localRepository.deleteAllVehicles() }
        runBlockingTest { vehicles = localRepository.getOfflineVehicles() }

        assertThat(vehicles, `is`(emptyList()))
    }

}