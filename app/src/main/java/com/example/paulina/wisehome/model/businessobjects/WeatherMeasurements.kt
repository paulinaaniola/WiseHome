package com.example.paulina.wisehome.model.businessobjects

import java.io.Serializable


class WeatherMeasurements(
        val temperature: Long,
        val humidity: Long,
        val createdAt: Long) : Serializable