package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.Alarms


interface GetAlarmsReciever {
    fun onGetAlarmsSuccess(alarms : Alarms)
    fun onGetAlarmsError()
}