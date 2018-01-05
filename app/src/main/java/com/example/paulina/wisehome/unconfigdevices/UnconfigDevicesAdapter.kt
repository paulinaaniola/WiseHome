package com.example.paulina.wisehome.unconfigdevices

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import com.example.paulina.wisehome.model.utils.ResUtil
import kotlinx.android.synthetic.main.item_device_group.view.*


internal class UnconfigDevicesAdapter(private val context: Context) : RecyclerView.Adapter<UnconfigDevicesAdapter.ViewHolder>() {

    var devices: List<UnconfigDevice> = emptyList<UnconfigDevice>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var numberOfLights: Int = 1
    var numberOfBlinds: Int = 1
    var numberOfWeatherSensors: Int = 1
    var numberOfAlarmSensors: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_device_group, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deviceType: DeviceType = devices[position].deviceType
        holder.deviceTypeNameTextView.text = deviceType.toStringLocale()
        setupDevicesList(deviceType, holder)
        holder.deviceTypeLayout.setOnClickListener(({ view -> (context as UnconfigDevicesView).onDeviceClick(devices[position]) }))
    }

    private fun setupDevicesList(deviceType: DeviceType, holder: UnconfigDevicesAdapter.ViewHolder) {
        if (deviceType == DeviceType.LIGHTS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.light_bulb))
            holder.deviceTypeNameTextView.text = ResUtil.getString(R.string.light) + " " + numberOfLights.toString()
            numberOfLights++
        } else if (deviceType == DeviceType.BLINDS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.blinds))
            holder.deviceTypeNameTextView.text = ResUtil.getString(R.string.blind) + " " + numberOfBlinds.toString()
            numberOfBlinds++
        } else if (deviceType == DeviceType.ALARM_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.alarm))
            holder.deviceTypeNameTextView.text = ResUtil.getString(R.string.alarm_sensor) + " " + numberOfAlarmSensors.toString()
            numberOfAlarmSensors
        } else if (deviceType == DeviceType.WEATHER_SENSORS) {
            holder.deviceTypeImageView.setImageDrawable(ResUtil.getDrawable(R.drawable.temperature))
            holder.deviceTypeNameTextView.text = ResUtil.getString(R.string.weather_sensor) + " " + numberOfWeatherSensors.toString()
            numberOfWeatherSensors++
        }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val deviceTypeImageView = view.deviceTypeImageView
        val deviceTypeNameTextView = view.deviceTypeNameTextView
        val deviceTypeLayout = view.deviceTypeLayout
    }
}