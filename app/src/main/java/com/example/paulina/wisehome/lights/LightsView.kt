package com.example.paulina.wisehome.lights

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.Lights

interface LightsView : BaseView {
    fun setLights(lights: Lights)
    fun onBulbSwitchClick(bulbId: String, isPowerOn: Boolean)
    fun updateLighBulbsState(id: String, isPoweredOn: Boolean?)
    fun setupRoomName(roomName : String)
    fun setAutomaticModeSwitch(isPoweredOn: Boolean)
}