package com.example.paulina.wisehome.rooms

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceGroupType
import com.example.paulina.wisehome.model.businessobjects.Room

class RoomsPresenterImpl : BaseAbstractPresenter<RoomsView>(), RoomsPresenter {

    private val presentationModel: RoomsModel by lazy { RoomsModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: RoomsView?) {
        super.onViewAttached(view)
        getRooms()
    }

    fun getRooms() {
        onGetRoomsSuccess(createDummyRooms())
    }

    fun onGetRoomsSuccess(rooms: List<Room>) {
        if(view != null) {
            view?.setRooms(rooms)
        }
    }

    fun createDummyRooms(): List<Room> {
        val rooms: MutableList<Room> = ArrayList<Room>()
        val deviceGroupTypes1: MutableList<DeviceGroupType> = ArrayList<DeviceGroupType>()
        val deviceGroupTypes2: MutableList<DeviceGroupType> = ArrayList<DeviceGroupType>()
        deviceGroupTypes1.add(DeviceGroupType.BLINDS)
        deviceGroupTypes1.add(DeviceGroupType.LIGHTS)
        deviceGroupTypes1.add(DeviceGroupType.ALARM_SENSORS)
        deviceGroupTypes1.add(DeviceGroupType.WEATHER_SENSORS)
        deviceGroupTypes2.add(DeviceGroupType.LIGHTS)
        deviceGroupTypes2.add(DeviceGroupType.BLINDS)
        rooms.add(Room("Living Room", 1, deviceGroupTypes1))
        rooms.add(Room("Kitchen", 2, deviceGroupTypes2))
        rooms.add(Room("Bedroom", 3, deviceGroupTypes1))
        return rooms
    }
}

