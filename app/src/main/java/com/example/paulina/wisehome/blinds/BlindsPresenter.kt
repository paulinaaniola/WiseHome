package com.example.paulina.wisehome.blinds

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.transportobjects.Blind

interface BlindsPresenter : BasePresenter {
    fun onGetBlindsSucces(blinds: List<Blind>)
    fun onGetBlindsError()
}
