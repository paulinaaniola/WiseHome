package com.example.paulina.wisehome.service.api

import com.example.paulina.wisehome.model.transportobjects.NewCredentials
import com.example.paulina.wisehome.model.transportobjects.NewUser
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface AccountsApi {

    @POST("api/homeId/users/changePassword")
    fun changePassword(@Body newCredentials: NewCredentials): Observable<Void>

    @POST("api/homeId/users/newUser")
    fun addNewUser(@Body newUser: NewUser): Observable<Void>
}
