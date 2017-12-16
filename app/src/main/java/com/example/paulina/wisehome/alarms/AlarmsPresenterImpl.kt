package com.example.paulina.wisehome.alarms

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter

class AlarmsPresenterImpl : BaseAbstractPresenter<AlarmsView>(), AlarmsPresenter {

    private val presentationModel: AlarmsModel by lazy { AlarmsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("room_id")
    }
}