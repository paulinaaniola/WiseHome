package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.businessobjects.NewWeather


interface GetWeatherReciever {

    fun onGetWeatherSuccess(weather: NewWeather)
    fun onGetWeatherError()
}