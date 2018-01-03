package com.example.paulina.wisehome.alarms

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetAlarmsReciever

class AlarmsPresenterImpl : BaseAbstractPresenter<AlarmsView>(), AlarmsPresenter, GetAlarmsReciever {

    private val presentationModel: AlarmsModel by lazy { AlarmsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("room_id")
    }

    override fun onViewAttached(view: AlarmsView?) {
        super.onViewAttached(view)
        getAlarms()
    }

    private fun getAlarms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onGetAlarmsSuccess()
    }

    override fun onGetAlarmsSuccess() {
        view?.stopProgressDialog()
        view?.setupAlarmsDrawables(true, false)
    }

    override fun onGetAlarmsError() {
        view?.stopProgressDialog()
    }
}