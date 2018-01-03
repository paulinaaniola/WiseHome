package com.example.paulina.wisehome.adddevice

import com.example.paulina.wisehome.base.BaseModel
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice


class AddDeviceModel : BaseModel{
    lateinit var selectedDevice: UnconfigDevice
    lateinit var newDeviceName: String
    lateinit var selectedRoom: Room
}
