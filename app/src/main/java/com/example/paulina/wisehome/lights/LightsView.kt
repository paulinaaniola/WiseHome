package com.example.paulina.wisehome.lights

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.businessobjects.LightBulb

interface LightsView : BaseView {
    fun setLights(lights: List<LightBulb>)
}