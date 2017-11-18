package com.example.paulina.wisehome.rooms

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter

class RoomsPresenterImpl : BaseAbstractPresenter<RoomsView>(), RoomsPresenter {

    private val presentationModel: RoomsModel by lazy { RoomsModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }
}

