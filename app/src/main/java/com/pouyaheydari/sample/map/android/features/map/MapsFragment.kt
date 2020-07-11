package com.pouyaheydari.sample.map.android.features.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
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

/**
 * MapsFragment to show the available cars on map to the user
 *
 */
class MapsFragment : BaseFragment(), OnMapReadyCallback {

    private val viewModel: MapsViewModel by viewModel()
    private var savedInstanceState: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun tryAgainDialogAction() {
        viewModel.getVehicles()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.savedInstanceState = savedInstanceState
        observeErrorMessage(viewModel.getExceptionData())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun showMarkers(googleMap: GoogleMap?, vehicles: List<Vehicle>) {
        vehicles.forEach { vehicle ->
            val location = LatLng(vehicle.lat, vehicle.lng)
            getBitmap(requireContext(), vehicle.image_url) { bitmap ->
                googleMap?.addMarker(
                    MarkerOptions()
                        .position(location)
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .rotation(vehicle.bearing)
                )
            }
        }
        zoomToTheLastVehicle(googleMap, vehicles.last())
    }

    private fun zoomToTheLastVehicle(googleMap: GoogleMap?, vehicle: Vehicle) {
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(vehicle.lat, vehicle.lng), 14F)
        )
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (savedInstanceState == null)
            viewModel.getVehicles()
        viewModel.getVehicleLiveData().observe(viewLifecycleOwner, Observer {
            showMarkers(googleMap, it.vehicles)
        })
    }
}