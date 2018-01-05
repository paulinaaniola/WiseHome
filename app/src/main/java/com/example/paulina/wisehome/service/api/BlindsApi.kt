package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.transportobjects.Blind
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface BlindsApi {
    @GET("api/homeId/rooms/{roomId}/BLINDS")
    fun getBlinds(
            @Path("roomId") patientId: String): Observable<List<Blind>>

    @POST("api/homeId/changeDeviceState/{deviceId}")
    fun changeBlindState(
            @Path("deviceId") deviceId: String,
            @Query("direction") direction: BlindDirection): Observable<Void>
}