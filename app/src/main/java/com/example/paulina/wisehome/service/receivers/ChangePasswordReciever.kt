package com.example.paulina.wisehome.service.receivers


interface ChangePasswordReciever {
    fun onChangePasswordSuccess()
    fun onChangePasswordError(throwable: Throwable)
}