package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import retrofit2.http.GET
import rx.Observable


interface UnconfigDevicesApi {

    @GET("api/id/rooms")
    fun getUnconfigDevices(): Observable<List<UnconfigDevice>>
}
