package com.example.paulina.wisehome.model.transportobjects

import com.example.paulina.wisehome.model.businessobjects.DeviceType
import java.io.Serializable


class Room(val _id: String,
           val roomName: String,
           val deviceGroups: List<DeviceType>): Serializable