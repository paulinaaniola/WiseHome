package com.example.paulina.wisehome.model.transportobjects

import com.example.paulina.wisehome.model.businessobjects.DeviceType
import java.io.Serializable


class UnconfigDevice(val mac: String,
                     val ip: String,
                     val deviceType: DeviceType) : Serializable