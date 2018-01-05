package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface WeatherApi {
    @GET("api/homeId/weatherSensor/{roomId}")
    fun getWeather( @Path("roomId") roomId: String): Observable<Weather>
}
