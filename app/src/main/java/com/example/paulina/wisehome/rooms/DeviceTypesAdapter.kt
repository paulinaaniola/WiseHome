package com.example.paulina.wisehome.rooms

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.utils.ResUtil
import kotlinx.android.synthetic.main.item_device_group.view.*

internal class DeviceTypesAdapter(private val room: Room, private val context: Context) : RecyclerView.Adapter<DeviceTypesAdapter.ViewHolder>() {

    private val listOfDevices = room.deviceGroups

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_device_group, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.deviceTypeNameTextView.text = listOfDevices[position].name
        setupDevicesList(listOfDevices[position], holder)
        setupDividerView(position, holder)
    }

    override fun getItemCount(): Int {
        return listOfDevices.size
    }

    private fun setupDividerView(position: Int, holder: ViewHolder) {
        if (position == listOfDevices.size - 1) {
            holder.dividerView.visibility = View.GONE
        }
    }

    private fun setupDevicesList(deviceType: DeviceType, holder: ViewHolder) {
        if (deviceType == DeviceType.LIGHTS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.light_bulb))
            holder.deviceTypeLayout.setOnClickListener({ v -> onDeviceTypeClick(DeviceType.LIGHTS) })
        } else if (deviceType == DeviceType.BLINDS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.blinds))
            holder.deviceTypeLayout.setOnClickListener({ v -> onDeviceTypeClick(DeviceType.BLINDS) })
        } else if (deviceType == DeviceType.ALARM_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.alarm))
            holder.deviceTypeLayout.setOnClickListener({ v -> onDeviceTypeClick(DeviceType.ALARM_SENSORS) })
        } else if (deviceType == DeviceType.WEATHER_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.temperature))
            holder.deviceTypeLayout.setOnClickListener({ v -> onDeviceTypeClick(DeviceType.WEATHER_SENSORS) })
        }
    }

    private fun onDeviceTypeClick(deviceType: DeviceType) {
        (context as RoomsView).onDeviceTypeClick(room._id, room.roomName, deviceType)
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceTypeLayout = view.deviceTypeLayout
        val deviceTypeNameTextView = view.deviceTypeNameTextView
        val deviceTypeImageView = view.deviceTypeImageView
        val dividerView = view.dividerView
    }
}