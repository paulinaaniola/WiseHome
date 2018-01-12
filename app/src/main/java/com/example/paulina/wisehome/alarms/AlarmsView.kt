package com.example.paulina.wisehome.alarms

import com.example.paulina.wisehome.base.BaseView

interface AlarmsView : BaseView {
    fun setupAlarmsDrawables(carbonMonoxideAlarrm: Boolean, carboonDioxideAlarm: Boolean)
    fun setupRoomName(roomName: String)
}
