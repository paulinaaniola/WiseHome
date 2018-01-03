package com.example.paulina.wisehome.addaccount

import android.os.Bundle
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_account.*


@ActivityView(layout = R.layout.activity_add_account, presenter = AddAccountPresenterImpl::class)
class AddAccountActivity : NavDrawerActivity(), AddAccountView {
    @Presenter
    lateinit var presenter: AddAccountPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addButton.setOnClickListener({ onAddButtonClick() })
    }

    private fun onAddButtonClick() {
        checkLoginAndPassword()
        if (loginEditText.error != null && passwordEditText.error != null && repeatPasswordEditText.error != null) {
            presenter.addAccount(loginEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun checkLoginAndPassword() {
        val login = loginEditText.text
        val password: String = passwordEditText.text.toString()
        val passwordConfirmation: String = repeatPasswordEditText.text.toString()
        cleanErrors()
        if (login.isEmpty()) {
            loginEditText.error = getString(R.string.blank_edit_text_error)
        }
        if (password.isEmpty()) {
            passwordEditText.error = getString(R.string.blank_edit_text_error)
        }
        if (passwordConfirmation.isEmpty()) {
            repeatPasswordEditText.error = getString(R.string.blank_edit_text_error)
        }
        if (!password.isEmpty() && !passwordConfirmation.isEmpty() && password != passwordConfirmation) {
            repeatPasswordEditText.error = getString(R.string.password_error_message)
        }
    }

    private fun cleanErrors() {
        loginEditText.clearError()
        passwordEditText.clearError()
        repeatPasswordEditText.clearError()
    }
}

