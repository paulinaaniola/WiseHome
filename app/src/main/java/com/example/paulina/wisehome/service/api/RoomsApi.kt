package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Room
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface RoomsApi {

    @GET("patients/{id}")
    fun getRooms(
            @Path("id") patientId: String): Observable<List<Room>>
}
