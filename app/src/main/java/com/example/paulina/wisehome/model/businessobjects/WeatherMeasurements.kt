package com.example.paulina.wisehome.model.businessobjects

import org.threeten.bp.LocalDateTime


class WeatherMeasurements(
        val temperature : Int,
        val humidity : Int,
        val date: LocalDateTime
)