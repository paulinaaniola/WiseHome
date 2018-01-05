package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Room
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface RoomsApi {

    @GET("api/homeId/rooms")
    fun getRooms(): Observable<List<Room>>

    @POST("api/homeId/newRoom")
    fun addNewRoom(
            @Query("name") name: String): Observable<Void>

}
