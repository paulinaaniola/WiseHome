package com.example.paulina.wisehome.alarms

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_alarms, presenter = AlarmsPresenterImpl::class)
class AlarmsActivity : NavDrawerActivity(), AlarmsView {

    @Presenter
    lateinit var presenter: AlarmsPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }
}