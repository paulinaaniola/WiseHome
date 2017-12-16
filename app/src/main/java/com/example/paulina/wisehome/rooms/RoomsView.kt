package com.example.paulina.wisehome.rooms

import android.view.View
import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.Room


interface RoomsView : BaseView {
    fun setRooms(rooms: List<Room>)

    fun onLightsClick(roomId: String)

    fun onBlindsClick()

    fun onAlarmSensorsClick(roomId: String)

    fun onWeatherSensorsClick(roomId: String)

    fun expandDevicesLayout(viewToAnim: View, arrowUp: View, arrowDown: View)

    fun collapseDevicesLayout(viewToAnim: View, arrowUp: View, arrowDown: View)
}