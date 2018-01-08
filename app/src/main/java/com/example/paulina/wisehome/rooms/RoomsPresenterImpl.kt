package com.example.paulina.wisehome.rooms

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType
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

    fun getRooms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
       // onGetRoomsSuccess(createDummyRooms())
        ServiceManager.getRooms(this)
    }

    override fun onGetRoomsSuccess(rooms: List<Room>) {
        view?.stopProgressDialog()
        view?.setRooms(rooms)
    }

    override fun onGetRoomsError() {
        view?.stopProgressDialog()
    }

    fun createDummyRooms(): List<Room> {
        val rooms: MutableList<Room> = ArrayList<Room>()
        val deviceGroupTypes1: MutableList<DeviceType> = ArrayList<DeviceType>()
        val deviceGroupTypes2: MutableList<DeviceType> = ArrayList<DeviceType>()
        deviceGroupTypes1.add(DeviceType.BLINDS)
        deviceGroupTypes1.add(DeviceType.LIGHTS)
        deviceGroupTypes1.add(DeviceType.ALARM_SENSORS)
        deviceGroupTypes1.add(DeviceType.WEATHER_SENSORS)
        deviceGroupTypes2.add(DeviceType.LIGHTS)
        deviceGroupTypes2.add(DeviceType.BLINDS)
        rooms.add(Room(1.toString(), "Living Room", deviceGroupTypes1))
        rooms.add(Room(2.toString(), "Kitchen", deviceGroupTypes2))
        rooms.add(Room(3.toString(), "Bedroom", deviceGroupTypes1))
        rooms.add(Room(4.toString(), "Bathroom1", deviceGroupTypes1))
        rooms.add(Room(5.toString(), "Bathroom2", deviceGroupTypes1))
        return rooms
    }

    override fun getAnimDuration(): Int {
        return presentationModel.ANIM_DURATION
    }
}

