package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.Weather


interface WeatherView : BaseView {
    fun setupWeatherState(weather : Weather)
}