package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.businessobjects.NewWeather
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface WeatherApi {
    @GET("api/homeId/rooms/{roomId}/WEATHER_SENSORS")
    fun getWeather(@Path("roomId") roomId: String): Observable<NewWeather>
}
