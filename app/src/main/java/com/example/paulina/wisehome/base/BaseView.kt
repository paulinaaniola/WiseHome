package com.example.paulina.wisehome.base

import android.content.Context

interface BaseView {

    val activityContext: Context

    fun startProgressDialog(message: String)

    fun stopProgressDialog()

}