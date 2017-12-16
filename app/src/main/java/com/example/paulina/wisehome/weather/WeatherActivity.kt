package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_weather, presenter = WeatherPresenterImpl::class)
class WeatherActivity : NavDrawerActivity(), WeatherView {

    @Presenter
    lateinit var presenter: WeatherPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }
}