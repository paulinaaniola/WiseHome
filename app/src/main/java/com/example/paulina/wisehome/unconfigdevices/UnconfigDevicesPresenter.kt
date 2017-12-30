package com.example.paulina.wisehome.unconfigdevices

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice


interface UnconfigDevicesPresenter : BasePresenter{
    fun saveSelectedDevice(selectedDevice: UnconfigDevice)
    fun getSelectedDevice() : UnconfigDevice
}