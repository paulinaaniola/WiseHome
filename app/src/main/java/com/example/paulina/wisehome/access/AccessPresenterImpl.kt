package com.patientcard.access

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.AccountType
import com.example.paulina.wisehome.model.transportobjects.IsAdmin
import com.example.paulina.wisehome.service.receivers.LoginReciever

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter, LoginReciever {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun login(login: String, password: String) {
      //  ServiceManager.login(this, Credentials(login, password))
        onLoginSuccess(IsAdmin(true))
    }

    override fun onLoginSuccess(isAdmin: IsAdmin) {
        if (isAdmin.isAdmin) {
            database.putAccountType(AccountType.ADMIN)
        } else {
            database.putAccountType(AccountType.REGULAR_USER)
        }
        view?.navigateToRooms()
    }

    override fun onLoginError() {
    }
}
