package com.example.paulina.wisehome.addaccount

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter


@ActivityView(layout = R.layout.activity_add_account, presenter = AddAccountPresenterImpl::class)
class AddAccountActivity : NavDrawerActivity(), AddAccountView {
    @Presenter
    lateinit var presenter: AddAccountPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }
}