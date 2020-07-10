package com.pouyaheydari.sample.map.android.features.vehiclelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pouyaheydari.sample.map.android.R
import com.pouyaheydari.sample.map.android.pojo.Vehicle
import com.pouyaheydari.sample.map.android.pojo.VehicleTypeEnum
import kotlinx.android.synthetic.main.vehicle_list_item.view.*

class VehicleListAdapter :
    ListAdapter<Vehicle, VehicleListAdapter.VehicleListViewHolder>(VehicleListDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_list_item, parent, false)
        return VehicleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VehicleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Vehicle) {
            with(itemView) {
                with(item) {
                    txtType.text = context.getString(R.string.vehicle_type, type)
                    txtLat.text = context.getString(R.string.vehicle_lat, lat.toString())
                    txtLng.text = context.getString(R.string.vehicle_lng, lng.toString())
                    txtBearing.text =
                        context.getString(R.string.vehicle_bearing, bearing.toString())

                    when (type) {
                        VehicleTypeEnum.ECO.type -> cardRoot.setCardBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPrimary
                            )
                        )
                        VehicleTypeEnum.PLUS.type -> cardRoot.setCardBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPlus
                            )
                        )
                        else -> cardRoot.setCardBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPrimaryDark
                            )
                        )
                    }
                }
            }
        }

    }

}