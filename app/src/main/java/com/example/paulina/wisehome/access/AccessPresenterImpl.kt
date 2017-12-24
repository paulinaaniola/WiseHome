package com.patientcard.access

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.AccountType

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun saveAccountType(){
        database.putAccountType(AccountType.ADMIN)
    }
}
