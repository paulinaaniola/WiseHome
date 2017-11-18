package com.example.paulina.wisehome.splash

import android.os.Handler
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseActivity
import com.example.paulina.wisehome.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_splash, presenter = SplashPresenterImpl::class)
class SplashActivity : BaseActivity(), SplashView {

    @Presenter
    lateinit var presenter: SplashPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.handleSplashScreen(Handler())
    }

    override fun openAccessActivity() {
        finish()
    }
}