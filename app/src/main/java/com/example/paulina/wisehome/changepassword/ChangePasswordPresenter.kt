package com.example.paulina.wisehome.changepassword

import com.example.paulina.wisehome.base.BasePresenter


interface ChangePasswordPresenter : BasePresenter{
    fun changePassword(newPassword : String, oldPassword: String)
}