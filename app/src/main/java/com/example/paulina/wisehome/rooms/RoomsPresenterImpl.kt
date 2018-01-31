package com.example.paulina.wisehome.rooms

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.GetRoomsReciever

class RoomsPresenterImpl : BaseAbstractPresenter<RoomsView>(), RoomsPresenter, GetRoomsReciever {

    private val presentationModel: RoomsModel by lazy { RoomsModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: RoomsView?) {
        super.onViewAttached(view)
        getRooms()
    }

    override fun getRooms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getRooms(this)
    }

    override fun onGetRoomsSuccess(rooms: List<Room>) {
        view?.stopProgressDialog()
        view?.setRooms(rooms)
        view?.setupRoomsEmptyView(rooms.isEmpty())
    }

    override fun onGetRoomsError() {
        view?.stopProgressDialog()
    }

    override fun getAnimDuration(): Int {
        return presentationModel.ANIM_DURATION
    }
}

