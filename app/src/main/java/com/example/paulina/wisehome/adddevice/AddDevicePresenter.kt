package com.example.paulina.wisehome.adddevice

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.Room


interface AddDevicePresenter : BasePresenter{
    fun getSelectedDeviceType() : DeviceType
    fun saveNewDeviceName(newName : String)
    fun saveSelectedRoom(selectedRoom : Room)
    fun addDeviceToRoom()
}