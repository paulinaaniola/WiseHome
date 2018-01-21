package com.example.paulina.wisehome.addaccount

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.transportobjects.NewUser
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.AddAccountReciever


class AddAccountPresenterImpl : BaseAbstractPresenter<AddAccountView>(), AddAccountPresenter, AddAccountReciever {

    override fun initExtras(intent: Intent) {
    }

    override fun addAccount(login: String, password: String, isAdmin: Boolean) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.addAccount(this, NewUser(login, password, isAdmin))
        onAddAccountSuccess()
    }

    override fun onAddAccountSuccess() {
        view?.stopProgressDialog()
        (view as AddAccountActivity).finish()
    }

    override fun onAddAccountError() {
        view?.stopProgressDialog()
    }
}
