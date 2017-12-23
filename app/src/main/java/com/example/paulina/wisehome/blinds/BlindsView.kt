package com.example.paulina.wisehome.blinds

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.Blind

interface BlindsView : BaseView{
    fun setBlinds(blinds : List<Blind>)
}
