package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Room
import retrofit2.http.GET
import rx.Observable

interface RoomsApi {

    @GET("api/homeId/rooms")
    fun getRooms(): Observable<List<Room>>
}
