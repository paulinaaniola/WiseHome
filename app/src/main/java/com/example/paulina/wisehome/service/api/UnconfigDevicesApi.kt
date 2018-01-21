package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable


interface UnconfigDevicesApi {

    @GET("api/homeId/unconfiguredDevice")
    fun getUnconfigDevices(): Observable<List<UnconfigDevice>>

    @POST("api/homeId/unconfiguredDevice/highlightDevice")
    fun highlightDevice(@Query("mac") mac: String,
                        @Query("highlight") highlight: Boolean): Observable<Boolean>


    @POST("api/homeId/newDevice")
    fun addDeviceToRoom(@Query("roomId") roomId: String,
                        @Query("deviceName") deviceName: String,
                        @Query("mac") mac: String): Observable<Void>

}
