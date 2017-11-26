package com.example.paulina.wisehome.rooms

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.utils.ResUtil
import kotlinx.android.synthetic.main.item_device_group.view.*

internal class DeviceTypesAdapter(private val deviceTypes: List<DeviceType>) : RecyclerView.Adapter<DeviceTypesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_device_group, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.deviceTypeNameTextView.text = deviceTypes[position].name
        setupDeviceTypeImage(deviceTypes[position], holder)
        setupDividerView(position, holder)
    }

    override fun getItemCount(): Int {
        return deviceTypes.size
    }

    private fun setupDividerView(position: Int, holder: ViewHolder) {
        if (position == deviceTypes.size - 1) {
            holder.dividerView.visibility = View.GONE
        }
    }

    private fun setupDeviceTypeImage(deviceType: DeviceType, holder: ViewHolder) {
        if (deviceType == DeviceType.LIGHTS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.light_bulb))
        } else if (deviceType == DeviceType.BLINDS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.blinds))
        } else if (deviceType == DeviceType.ALARM_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.alarm))
        } else if (deviceType == DeviceType.WEATHER_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.temperature))
        }
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceTypeNameTextView = view.deviceTypeNameTextView
        val deviceTypeImageView = view.deviceTypeImageView
        val dividerView = view.dividerView
    }
}