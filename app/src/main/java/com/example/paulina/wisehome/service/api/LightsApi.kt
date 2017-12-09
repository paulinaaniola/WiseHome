package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Lights
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface LightsApi {
    @GET("api/homeId/rooms/{roomId}/LIGHTS")
    fun getLights(
            @Path("roomId") patientId: String): Observable<Lights>
}
