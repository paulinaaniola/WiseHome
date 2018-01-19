package com.example.paulina.wisehome.model.transportobjects

import com.example.paulina.wisehome.model.businessobjects.BlindState
import java.io.Serializable

class Blind(val _id: String,
            val name: String,
            var state: BlindState) : Serializable