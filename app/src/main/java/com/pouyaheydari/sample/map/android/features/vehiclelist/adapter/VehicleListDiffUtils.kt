package com.pouyaheydari.sample.map.android.features.vehiclelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pouyaheydari.sample.map.android.pojo.Vehicle

/**
 * DiffUtils for [VehicleListAdapter]
 *
 */
class VehicleListDiffUtils : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle) =
        oldItem.lat == newItem.lat && oldItem.lng == newItem.lng && oldItem.type == newItem.type && oldItem.bearing == newItem.bearing

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle) =
        oldItem.lat == newItem.lat && oldItem.lng == newItem.lng && oldItem.type == newItem.type && oldItem.bearing == newItem.bearing && oldItem.image_url == newItem.image_url
}