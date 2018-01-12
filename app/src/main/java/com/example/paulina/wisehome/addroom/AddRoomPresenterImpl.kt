package com.example.paulina.wisehome.addroom

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.AddRoomReciever


class AddRoomPresenterImpl : BaseAbstractPresenter<AddRoomView>(), AddRoomPresenter, AddRoomReciever {


    override fun initExtras(intent: Intent) {
    }

    override fun addRoom(roomName: String) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.addNewRoom(this, roomName)
        //onAddRoomSuccess()
    }

    override fun onAddRoomSuccess() {
        view?.stopProgressDialog()
        view?.navigateToRooms()
    }

    override fun onAddRoomError() {
        view?.stopProgressDialog()
    }
}
