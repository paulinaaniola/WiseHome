package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.Blind


interface GetBlindsReciever {

    fun onGetBlindsSuccess(blinds : List<Blind>)

    fun onGetBlindsError()
}