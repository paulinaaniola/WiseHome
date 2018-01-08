package com.example.paulina.wisehome.blinds

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.transportobjects.Blind

interface BlindsView : BaseView{
    fun setBlinds(blinds : List<Blind>)
    fun onChangeBlindDirectionClick(direction : BlindDirection, blindId: String)
    fun updateBlindState(id: String, state : String)
}
