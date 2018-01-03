package com.example.paulina.wisehome.colorlist

import com.example.paulina.wisehome.base.BaseModel
import com.example.paulina.wisehome.model.businessobjects.Color


class ColorListModel : BaseModel {
    lateinit var roomId: String
    var colorRelax: Color = Color("Relax", 6594610)
    var colorStudy: Color = Color("Study", 6594815)
    var colorWork: Color = Color("Work", 16777060)
}
