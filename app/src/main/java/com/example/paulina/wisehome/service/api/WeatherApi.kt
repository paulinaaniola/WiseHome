package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Weather
import retrofit2.http.GET
import rx.Observable


interface WeatherApi {
    @GET("api/homeId/rooms")
    fun getWeather(): Observable<Weather>
}
