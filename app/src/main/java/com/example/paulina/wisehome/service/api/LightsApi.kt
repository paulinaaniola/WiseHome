package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import retrofit2.http.*
import rx.Observable

interface LightsApi {
    @GET("api/homeId/rooms/{roomId}/LIGHTS")
    fun getLights(
            @Path("roomId") patientId: String): Observable<Lights>

    @POST("api/homeId/deviceState/LIGHTS/color/{roomId}")
    fun changeLightColor(
            @Path("roomId") patientId: String,
            @Body color: RGBColor): Observable<Void>

    @POST("api/homeId/deviceState/LIGHTS/power/{deviceId}")
    fun turnOnOffLight(
            @Path("deviceId") deviceId: String,
            @Query("power") power: Boolean): Observable<Void>

    @POST("api/homeId/rooms/{roomId}/automaticLightsPower")
    fun setAutomaticWork(
            @Path("roomId") roomId: String,
            @Query("automaticMode") automaticMode: Boolean): Observable<Void>
}
