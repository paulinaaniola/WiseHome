package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Alarms
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface AlarmsApi {
    @GET("api/homeId/alarmSensor/{roomId}")
    fun getAlarms(
            @Path("roomId") roomId: String): Observable<Alarms>

}