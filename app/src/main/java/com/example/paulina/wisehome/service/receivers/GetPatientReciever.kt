package com.example.paulina.wisehome.service.receivers

interface GetPatientReciever {

    fun onGetPatientSuccess(patient: Int)

    fun onGetPatientError()
}