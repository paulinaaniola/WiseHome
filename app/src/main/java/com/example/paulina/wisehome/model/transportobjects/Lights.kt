package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Lights(
        val automaticMode: Boolean,
        val currentColor: RGBColor,
        val lightBulbs: List<LightBulb>) : Serializable
