package com.example.paulina.wisehome.addroom

import com.example.paulina.wisehome.base.BasePresenter


interface AddRoomPresenter : BasePresenter {
    fun addRoom(roomName: String)
}