package com.example.paulina.wisehome.rooms

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseActivity
import com.example.paulina.wisehome.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_rooms, presenter = RoomsPresenterImpl::class)
class RoomsActivity : BaseActivity(), RoomsView {

    @Presenter
    lateinit var presenter: RoomsPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }
}