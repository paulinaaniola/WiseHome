package com.example.paulina.wisehome.colorlist

import com.example.paulina.wisehome.base.BaseView
import com.example.paulina.wisehome.model.transportobjects.RGBColor


interface ColorListView : BaseView {
    fun onColorClick(color : RGBColor)
}