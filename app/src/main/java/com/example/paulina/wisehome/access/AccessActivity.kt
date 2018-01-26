package com.patientcard.access

import android.content.Intent
import android.os.Bundle
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseActivity
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.utils.ResUtil
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
        val login: String = loginEditText.text.toString()
        val password = passwordEditText.text.toString()
        if(login.isEmpty()){
            loginEditText.error = ResUtil.getString(R.string.blank_edit_text_error)
        } else {
            loginEditText.clearError()
        }

        if(password.isEmpty()){
            passwordEditText.error = ResUtil.getString(R.string.blank_edit_text_error)
        } else {
            passwordEditText.clearError()
        }

        if(!login.isEmpty() && !password.isEmpty()){
            presenter.login(login, password)
        }
    }

    override fun navigateToRooms() {
        startActivity(Intent(this, RoomsActivity::class.java))
    }

    override fun onStop() {
        super.onStop()
        loginEditText.text.clear()
        passwordEditText.text.clear()
    }
}
