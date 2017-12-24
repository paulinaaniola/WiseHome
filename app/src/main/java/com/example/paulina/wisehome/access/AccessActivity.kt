package com.patientcard.access

import android.content.Intent
import android.os.Bundle
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseActivity
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.rooms.RoomsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_access.*

@ActivityView(layout = R.layout.activity_access, presenter = AccessPresenterImpl::class)
class AccessActivity : BaseActivity(), AccessView {

    @Presenter
    lateinit var presenter: AccessPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoginButton()
    }

    fun setupLoginButton() {
        loginButton.setOnClickListener(({ v -> onLoginButtonClick() }))
    }

    private fun onLoginButtonClick() {
        presenter.saveAccountType()
        startActivity(Intent(this, RoomsActivity::class.java))
    }
}
