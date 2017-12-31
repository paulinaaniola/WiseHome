package com.example.paulina.wisehome.colorlist

import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.businessobjects.Color


interface ColorListPresenter : BasePresenter{
    fun createColorsList(): List<Color>
}