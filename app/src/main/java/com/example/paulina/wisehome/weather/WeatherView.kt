package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.businessobjects.NewWeather
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements


interface WeatherView : BaseView {
    fun setupWeatherState(weather: NewWeather)
    fun setupRoomName(roomName: String)
    fun setupCharts(historicMeasurements : List<WeatherMeasurements>)
}