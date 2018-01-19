package com.example.paulina.wisehome.service.receivers

import com.example.paulina.wisehome.model.transportobjects.IsAdmin


interface LoginReciever {
    fun onLoginSuccess(isAdmin: IsAdmin)
    fun onLoginError()

}