package com.pouyaheydari.sample.map.android.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pouyaheydari.sample.map.android.R
import com.pouyaheydari.sample.map.android.base.BaseFragment
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.utils.getBitmap
import org.koin.android.viewmodel.ext.android.viewModel

class MapsFragment : BaseFragment(), OnMapReadyCallback {

    private val viewModel: MapsViewModel by viewModel()

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
        mapFragment?.getMapAsync(this)
    }

    private fun showMarkers(googleMap: GoogleMap?, vehicles: List<Vehicle>) {
        vehicles.forEach { vehicle ->
            val location = LatLng(vehicle.lat, vehicle.lng)
            getBitmap(vehicle.image_url) { bitmap ->
                googleMap?.addMarker(
                    MarkerOptions()
                        .position(location)
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .rotation(vehicle.bearing)
                )
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        viewModel.getVehicleLiveData().observe(viewLifecycleOwner, Observer {
            showMarkers(googleMap, it.vehicles)
        })
    }
}