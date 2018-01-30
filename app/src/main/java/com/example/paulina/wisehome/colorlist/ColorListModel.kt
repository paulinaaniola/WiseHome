package com.example.paulina.wisehome.colorlist

import com.example.paulina.wisehome.base.BaseModel
import com.example.paulina.wisehome.model.businessobjects.Color
import com.example.paulina.wisehome.model.transportobjects.RGBColor


class ColorListModel : BaseModel {
    lateinit var roomId: String
    var colorRelax: Color = Color("Sunset", RGBColor("255", "71", "21"))
    var colorWork: Color = Color("Work", RGBColor("238", "247", "255"))
    var colorOcean: Color = Color("Ocean", RGBColor("65", "255", "232"))
    var colorRomantic: Color = Color("Romantic", RGBColor("255", "19", "119"))
}
