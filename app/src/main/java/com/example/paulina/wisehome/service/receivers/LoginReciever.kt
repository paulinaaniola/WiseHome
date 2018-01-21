package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.LoggedUser


interface LoginReciever {
    fun onLoginSuccess(isAdmin: LoggedUser)
    fun onLoginError()

}