package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.Credentials
import com.example.paulina.wisehome.model.transportobjects.IsAdmin
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface LoginApi {
    @POST("api/homeId/login")
    fun login(@Body credentials: Credentials): Observable<IsAdmin>
}
