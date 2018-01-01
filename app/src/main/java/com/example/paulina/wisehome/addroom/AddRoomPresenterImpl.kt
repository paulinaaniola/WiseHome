package com.example.paulina.wisehome.addroom

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter


class AddRoomPresenterImpl : BaseAbstractPresenter<AddRoomView>(), AddRoomPresenter {

    override fun initExtras(intent: Intent) {
    }

    override fun addRoom(roomName: String) {
        onAddRoomSuccess()
    }

    fun onAddRoomSuccess() {
        view?.navigateToRooms()
    }
}
