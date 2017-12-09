package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Room(val _id: String,
           val name: String,
           val listOfDeviceTypes: List<DeviceType>): Serializable