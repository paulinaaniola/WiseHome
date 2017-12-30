package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Lights(
        val currentColor: RGBColor,
        val lightBulbs: List<LightBulb>) : Serializable
