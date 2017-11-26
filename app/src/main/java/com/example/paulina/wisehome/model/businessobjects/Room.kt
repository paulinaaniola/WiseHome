package com.example.paulina.wisehome.model.businessobjects

import java.io.Serializable


class Room(val name: String,
           val id: Int,
           val listOfDeviceGroupTypes: List<DeviceGroupType>) : Serializable