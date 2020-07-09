package com.pouyaheydari.sample.map.android.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pouyaheydari.sample.map.android.R
import com.pouyaheydari.sample.map.android.base.BaseFragment
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import org.koin.android.viewmodel.ext.android.viewModel

class MapsFragment : BaseFragment() {

    private val viewModel: MapsViewModel by viewModel()
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    private val callback = OnMapReadyCallback { googleMap ->

        this.googleMap = googleMap
        viewModel.getVehicleLiveData().observe(viewLifecycleOwner, Observer {
            showMarkers(it.vehicles)
        })
    }

    private fun showMarkers(vehicles: List<Vehicle>) {
        vehicles.forEach {
            val location = LatLng(it.lat, it.lng)
            googleMap.addMarker(MarkerOptions().position(location))
        }
    }

}