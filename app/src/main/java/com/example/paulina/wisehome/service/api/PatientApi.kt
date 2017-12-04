package com.example.paulina.wisehome.service.api

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PatientApi {

    @GET("patients/{id}")
    fun getPatient(
            @Path("id") patientId: String): Observable<Int>
}
