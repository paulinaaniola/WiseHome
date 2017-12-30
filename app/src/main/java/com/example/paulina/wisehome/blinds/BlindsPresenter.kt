package com.example.paulina.wisehome.blinds

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.businessobjects.BlindDirection

interface BlindsPresenter : BasePresenter{
    fun changeBlindDirection(direction : BlindDirection)
}