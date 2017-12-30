package com.example.paulina.wisehome.adddevice

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.Room


interface AddDeviceView : BaseView {

    fun setRooms(rooms: List<Room>)

    fun displayRoomConfigurationDialog(room: Room)
}