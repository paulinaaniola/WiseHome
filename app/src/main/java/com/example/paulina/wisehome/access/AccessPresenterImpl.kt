package com.patientcard.access

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.AccountType
import com.example.paulina.wisehome.model.transportobjects.LoggedUser
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.LoginReciever

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter, LoginReciever {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun login(login: String, password: String) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
       // ServiceManager.login(this, Credentials(login, password))
        onLoginSuccess(LoggedUser("paulina", true))
    }

    override fun onLoginSuccess(loggedUser: LoggedUser) {
        view?.stopProgressDialog()
        database.putLoggedUsername(loggedUser.username)
        if (loggedUser.isAdmin) {
            database.putAccountType(AccountType.ADMIN)
        } else {
            database.putAccountType(AccountType.REGULAR_USER)
        }
        view?.navigateToRooms()
    }

    override fun onLoginError() {
        view?.stopProgressDialog()
    }
}
