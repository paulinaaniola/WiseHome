package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Lights(
        val selectedColor: Int,
        val listOfLightBulb: List<LightBulb>) : Serializable
