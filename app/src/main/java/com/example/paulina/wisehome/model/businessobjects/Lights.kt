package com.example.paulina.wisehome.model.businessobjects

import java.io.Serializable


class Lights(
        val selectedColor: Int,
        val listOfLightBulb: List<LightBulb>) : Serializable
