package com.example.paulina.wisehome.adddevice

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetRoomsReciever


class AddDevicePresenterImpl : BaseAbstractPresenter<AddDeviceView>(), AddDevicePresenter, GetRoomsReciever {

    override fun initExtras(intent: Intent) {
        presentationModel.slectedDevice = intent.getSerializableExtra("SelectedDevice") as UnconfigDevice
    }

    private val presentationModel: AddDeviceModel by lazy { AddDeviceModel() }

    override fun onViewAttached(view: AddDeviceView?) {
        super.onViewAttached(view)
        getRooms()
    }

    private fun getRooms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onGetRoomsSuccess(createDummyRooms())
    }

    override fun onGetRoomsSuccess(rooms: List<Room>) {
        view?.setRooms(rooms)
        view?.stopProgressDialog()
    }

    override fun onGetRoomsError() {
        view?.stopProgressDialog()
    }

    override fun getSelectedDeviceType() : DeviceType {
       return presentationModel.slectedDevice.type
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
}