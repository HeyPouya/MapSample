package com.pouyaheydari.sample.map.android.features.vehiclelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pouyaheydari.sample.map.android.R
import com.pouyaheydari.sample.map.android.base.BaseFragment
import com.pouyaheydari.sample.map.android.features.vehiclelist.adapter.VehicleListAdapter
import kotlinx.android.synthetic.main.item_toolbar_back.*
import kotlinx.android.synthetic.main.vehicle_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Shows a list of previously fetched [com.pouyaheydari.sample.map.android.pojo.Vehicle] when user is offline
 *
 */
class VehicleListFragment : BaseFragment() {

    private val viewModel: VehicleListViewModel by viewModel()
    private lateinit var adapter: VehicleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_list_fragment, container, false)
    }

    override fun tryAgainDialogAction() {
        viewModel.getVehicles()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txtTitle.text = getString(R.string.vehicle_list)
        observeErrorMessage(viewModel.getExceptionData())
        setUpRecyclerView()
        viewModel.getVehicleListLiveData().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        if (savedInstanceState == null)
            viewModel.getVehicles()
    }

    private fun setUpRecyclerView() {
        adapter = VehicleListAdapter()
        recycler.adapter = adapter
    }
}