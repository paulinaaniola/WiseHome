package com.example.paulina.wisehome.unconfigdevices

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice

interface UnconfigDevicesView : BaseView {
    fun setDevices(devices : List<UnconfigDevice>)
    fun onDeviceClick(deviceType: DeviceType)}