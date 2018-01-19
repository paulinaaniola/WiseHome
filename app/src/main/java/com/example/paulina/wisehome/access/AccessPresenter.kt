package com.patientcard.access

import com.example.paulina.wisehome.base.BasePresenter

interface AccessPresenter : BasePresenter {
    fun login(login : String, password : String)
}

