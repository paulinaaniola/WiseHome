package com.example.paulina.wisehome.changepassword

import android.content.Intent
import android.widget.Toast
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.ApplicationContext
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.transportobjects.NewCredentials
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.model.utils.ToastUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.ChangePasswordReciever
import retrofit2.HttpException


class ChangePasswordPresenterImpl : BaseAbstractPresenter<ChangePasswordView>(), ChangePasswordPresenter, ChangePasswordReciever {
    override fun initExtras(intent: Intent) {
    }

    override fun changePassword(newPassword: String, oldPassword: String) {
        val newCredentials = NewCredentials(database.getUsername(), oldPassword, newPassword)
        ServiceManager.changePassword(this, newCredentials)
    }

    override fun onChangePasswordSuccess() {
        view?.navigateToRooms()
    }

    override fun onChangePasswordError(error: Throwable) {
        if (error is HttpException) {
            val context = ApplicationContext.appContext
            if (error.code() == 401) {
                ToastUtil.show(context, ResUtil.getString(R.string.invalid_old_password), Toast.LENGTH_LONG)
            }
        }
    }
}