package com.example.paulina.wisehome.service

import com.example.paulina.wisehome.service.api.PatientApi
import com.example.paulina.wisehome.service.api.RoomsApi

object ServiceProvider {

    var BASE_URL = "http://10.0.2.2:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)

    var roomsService: RoomsApi = ServiceFactory.createRetrofitService(RoomsApi::class.java, BASE_URL, false, false)
}