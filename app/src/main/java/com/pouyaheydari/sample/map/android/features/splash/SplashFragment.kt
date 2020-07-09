package com.pouyaheydari.sample.map.android.features.splash

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pouyaheydari.sample.map.android.R
import com.pouyaheydari.sample.map.android.base.BaseFragment
import com.pouyaheydari.sample.map.android.utils.ViewNavigationEnum
import com.pouyaheydari.sample.map.android.utils.isNetworkAvailable
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNextPageLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                ViewNavigationEnum.MAP -> findNavController().navigate(R.id.action_splashFragment_to_mapsFragment)
                ViewNavigationEnum.VEHICLE_LIST -> findNavController().navigate(R.id.action_splashFragment_to_vehicleListFragment)
                ViewNavigationEnum.OFFLINE_NO_DATA -> showOfflineNoDataError()
                else -> throw IllegalArgumentException("The navigation for ${it.name} is not provided in the fragment")
            }
        })
        viewModel.decideNextView(isNetworkAvailable(requireContext()))
    }

    private fun showOfflineNoDataError() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.no_data_available_to_show))
            .setMessage(getString(R.string.offline_open_no_data_error))
            .setPositiveButton(getString(R.string.ok)) { _: DialogInterface, _: Int -> requireActivity().finish() }
            .show()
    }

}