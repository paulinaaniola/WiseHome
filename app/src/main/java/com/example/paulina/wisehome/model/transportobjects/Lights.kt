package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class Lights(
        val currentColor: Long,
        val lightBulbs: List<LightBulb>) : Serializable
