package com.example.paulina.wisehome.lights

import com.example.paulina.wisehome.base.BaseModel
import com.google.firebase.database.DatabaseReference

class LightsModel : BaseModel {
    lateinit var roomId: String
    var isLightStateUpdate : Boolean = false
    var isAutomaticMode: Boolean = false
    lateinit var mDatabase: DatabaseReference
    lateinit var test: DatabaseReference
    lateinit var devicesStates: DatabaseReference
    lateinit var lightBulbPower: DatabaseReference
}