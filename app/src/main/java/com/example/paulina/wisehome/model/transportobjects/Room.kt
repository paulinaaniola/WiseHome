package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Room(val name: String,
           val id: Int,
           val listOfDeviceGroupTypes: List<DeviceType>) : Serializable