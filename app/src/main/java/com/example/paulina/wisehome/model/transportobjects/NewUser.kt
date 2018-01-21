package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class NewUser(val username : String,
              val password : String,
              val isAdmin : Boolean) : Serializable
