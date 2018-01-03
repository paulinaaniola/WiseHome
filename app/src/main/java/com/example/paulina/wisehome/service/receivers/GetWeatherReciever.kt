package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.Weather


interface GetWeatherReciever {

    fun onGetWeatherSuccess(weather: Weather)
    fun onGetWeatherError()
}