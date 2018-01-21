package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class LoggedUser(val username: String,
                 val isAdmin: Boolean) : Serializable
