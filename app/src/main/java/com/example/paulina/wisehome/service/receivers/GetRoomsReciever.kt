package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.Room

interface GetRoomsReciever {

    fun onGetRoomsSuccess(rooms: List<Room>)

    fun onGetRoomsError()
}