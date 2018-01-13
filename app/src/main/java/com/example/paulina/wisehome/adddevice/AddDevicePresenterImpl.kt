package com.example.paulina.wisehome.adddevice

import android.content.Intent
import android.os.Handler
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.AddDeviceToRoomReciever
import com.example.paulina.wisehome.service.receivers.GetRoomsReciever


class AddDevicePresenterImpl : BaseAbstractPresenter<AddDeviceView>(), AddDevicePresenter, GetRoomsReciever, AddDeviceToRoomReciever {

    override fun initExtras(intent: Intent) {
        presentationModel.selectedDevice = intent.getSerializableExtra("SelectedDevice") as UnconfigDevice
    }

    private val presentationModel: AddDeviceModel by lazy { AddDeviceModel() }

    override fun onViewAttached(view: AddDeviceView?) {
        super.onViewAttached(view)
        getRooms()
    }

    private fun getRooms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getRooms(this)
        //onGetRoomsSuccess(createDummyRooms())
    }

    override fun onGetRoomsSuccess(rooms: List<Room>) {
        view?.setRooms(rooms)
        view?.stopProgressDialog()
    }

    override fun onGetRoomsError() {
        view?.stopProgressDialog()
    }

    override fun getSelectedDeviceType(): DeviceType {
        return presentationModel.selectedDevice.deviceType
    }

    override fun saveNewDeviceName(newName: String) {
        presentationModel.newDeviceName = newName
    }

    override fun saveSelectedRoom(selectedRoom: Room) {
        presentationModel.selectedRoom = selectedRoom
    }

    override fun addDeviceToRoom() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        val pm = presentationModel
        ServiceManager.addDeviceToRoom(this, pm.selectedRoom._id, pm.newDeviceName, pm.selectedDevice.mac)
       // onAddDeviceToRoomSuccess()
    }

    override fun onAddDeviceToRoomSuccess() {
        view?.stopProgressDialog()
        view?.displayDeviceConfiguratedDialog()
        val handler = Handler()
        handler.postDelayed(Runnable {
            view?.navigateToRooms()
        }, presentationModel.dialogDuration)
    }

    override fun onAddDeviceToRoomError() {
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
}