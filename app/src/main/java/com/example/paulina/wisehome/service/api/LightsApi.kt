package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import retrofit2.http.*
import rx.Observable

interface LightsApi {
    @GET("api/homeId/rooms/{roomId}/LIGHTS")
    fun getLights(
            @Path("roomId") patientId: String): Observable<Lights>

    @POST("api/homeId/rooms/{roomId}/LIGHTS")
    fun changeLightColor(
            @Path("roomId") patientId: String,
            @Body color: RGBColor): Observable<Void>

    @POST("api/homeId/rooms/{roomId}/LIGHTS")
    fun turnOnOffLight(
            @Path("roomId") roomId: String,
            @Query("power") power: Boolean,
            @Query("deviceId") deviceId: String): Observable<Void>

}
