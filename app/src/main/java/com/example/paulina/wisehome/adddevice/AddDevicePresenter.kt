package com.example.paulina.wisehome.adddevice

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType


interface AddDevicePresenter : BasePresenter{
    fun getSelectedDeviceType() : DeviceType
}