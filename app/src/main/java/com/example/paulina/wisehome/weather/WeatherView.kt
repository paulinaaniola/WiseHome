package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.transportobjects.Weather


interface WeatherView : BaseView {
    fun setupWeatherState(weather: Weather)
    fun setupRoomName(roomName: String)
    fun setupFeverGraph(historicMeasurements: ArrayList<WeatherMeasurements>?)
}