package com.example.paulina.wisehome.model.transportobjects

import java.io.Serializable


class NewCredentials(val username: String,
                     val oldPassword: String,
                     val newPassword: String) : Serializable
