package com.example.paulina.wisehome.adddevice

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.rooms.RoomsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.dialog_match_device_to_room.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_add_device, presenter = AddDevicePresenterImpl::class)
class AddDeviceActivity : NavDrawerActivity(), AddDeviceView {
    @Presenter
    lateinit var presenter: AddDevicePresenter

    private var roomsAdapter: RoomsAdapter = RoomsAdapter(this)

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(toolbar, " 123")
        setupRoomsList()
        setupDialog()
    }

    override fun displayRoomConfigurationDialog(room: Room) {
        presenter.saveSelectedRoom(room)
        selectedRoomNameTextView.text = room.roomName
        selectedDeviceNameTextView.text = getNewDeviceName()
        dialogMatchDeviceToRoom.visibility = View.VISIBLE
    }

    private fun getNewDeviceName(): String {
        var deviceName: String
        if (deviceNameEditText.text.isEmpty()) {
            when (presenter.getSelectedDeviceType()) {
                DeviceType.LIGHTS -> deviceName = getString(R.string.light)
                DeviceType.BLINDS -> deviceName = getString(R.string.blind)
                DeviceType.WEATHER_SENSORS -> deviceName = getString(R.string.weather_sensor)
                DeviceType.ALARM_SENSORS -> deviceName = getString(R.string.alarm_sensor)
            }
        } else {
            deviceName = deviceNameEditText.text.toString()
        }
        presenter.saveNewDeviceName(deviceName)
        return deviceName
    }

    private fun setupRoomsList() {
        roomsRecyclerView.layoutManager = LinearLayoutManager(this)
        roomsRecyclerView.adapter = roomsAdapter
    }

    private fun setupDialog() {
        matchDeviceDialogBackground.setOnClickListener({ dialogMatchDeviceToRoom.visibility = View.GONE })
        addButton.setOnClickListener({ onAddButtonClick() })
        cancelButton.setOnClickListener({ dialogMatchDeviceToRoom.visibility = View.GONE })
    }

    override fun navigateToRooms() {
        dialogDeviceConfigurated.visibility = View.GONE
        startActivity(Intent(this, RoomsActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
    }

    private fun onAddButtonClick() {
        dialogMatchDeviceToRoom.visibility = View.GONE
        presenter.addDeviceToRoom()
    }

    override fun displayDeviceConfiguratedDialog() {
        dialogDeviceConfigurated.visibility = View.VISIBLE
    }

    override fun setRooms(rooms: List<Room>) {
        roomsAdapter.rooms = rooms
    }

    override fun onBackPressed() {
        if (dialogMatchDeviceToRoom.visibility == View.VISIBLE) {
            dialogMatchDeviceToRoom.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}