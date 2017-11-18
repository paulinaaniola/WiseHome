package com.example.paulina.wisehome.splash

import android.content.Intent
import android.os.Handler
import com.example.paulina.wisehome.base.BasePresenter

interface SplashPresenter : BasePresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    fun handleSplashScreen(handler: Handler)

}