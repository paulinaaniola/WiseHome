package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice


interface GetUnconfigDevicesReciever {

    fun onGetUnconfigDevicesSucces(devices: List<UnconfigDevice>)

    fun onGetUnconfigDevicesError()
}