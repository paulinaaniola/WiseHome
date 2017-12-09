package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.Lights

interface GetLightsReciever {

    fun onGetLightsSuccess(lights: Lights)

    fun onGetLightsError()
}