package com.example.paulina.wisehome.changepassword

import android.content.Intent
import android.os.Bundle
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.rooms.RoomsActivity
import com.rey.material.widget.EditText
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_change_password.*

@ActivityView(layout = R.layout.activity_change_password, presenter = ChangePasswordPresenterImpl::class)
class ChangePasswordActivity : NavDrawerActivity(), ChangePasswordView {

    @Presenter
    lateinit var presenter: ChangePasswordPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupChangePasswordButton()
    }

    fun setupChangePasswordButton() {
        changePasswordButton.setOnClickListener({ onChangePasswordClick() })
    }

    fun onChangePasswordClick() {
        setBlankEditTextError(oldPasswordEditText)
        setBlankEditTextError(passwordEditText)
        setBlankEditTextError(passwordConfirmEditText)

        val oldPassword = oldPasswordEditText.text.toString()
        val newPassword = passwordEditText.text.toString()
        val newPasswordConfirm = passwordConfirmEditText.text.toString()

        if (!newPassword.isEmpty() && !newPasswordConfirm.isEmpty() && !oldPassword.isEmpty() && newPassword.equals(newPasswordConfirm)) {
            presenter.changePassword(newPassword.toString(), oldPassword.toString())
        } else if (!newPassword.equals(newPasswordConfirm)) {
            passwordEditText.error = ResUtil.getString(R.string.password_not_match)
            passwordConfirmEditText.error = ResUtil.getString(R.string.password_not_match)
        }
    }

    fun setBlankEditTextError(editTextToCheck: EditText) {
        val text = editTextToCheck.text
        if (text.isEmpty()) {
            editTextToCheck.error = ResUtil.getString(R.string.blank_edit_text_error)
        } else {
            editTextToCheck.clearError()
        }
    }

    override fun navigateToRooms() {
        startActivity(Intent(this, RoomsActivity::class.java))
    }
}