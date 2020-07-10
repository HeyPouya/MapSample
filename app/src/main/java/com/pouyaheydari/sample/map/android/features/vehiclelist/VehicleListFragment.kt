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

class VehicleListFragment : BaseFragment() {

    private val viewModel: VehicleListViewModel by viewModel()
    private lateinit var adapter: VehicleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txtTitle.text = getString(R.string.vehicle_list)
        setUpRecyclerView()
        viewModel.getVehicleListLiveData().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setUpRecyclerView() {
        adapter = VehicleListAdapter()
        recycler.adapter = adapter
    }

}