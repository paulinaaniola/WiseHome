package com.example.paulina.wisehome.addaccount

import com.example.paulina.wisehome.base.BasePresenter


interface AddAccountPresenter : BasePresenter{
    fun addAccount(login: String, password: String, isAdmin : Boolean)
}