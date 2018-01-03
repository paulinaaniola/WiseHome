package com.example.paulina.wisehome.lights

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.transportobjects.RGBColor

interface LightsPresenter : BasePresenter {
    fun changeLightColor(color : RGBColor)
    fun turnOnOffLight(lightId: String, power: Boolean)
    fun getRoomId(): String
}