package com.example.paulina.wisehome.alarms

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.base.IntentKeys
import com.example.paulina.wisehome.model.transportobjects.Alarms
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.GetAlarmsReciever

class AlarmsPresenterImpl : BaseAbstractPresenter<AlarmsView>(), AlarmsPresenter, GetAlarmsReciever {

    private val presentationModel: AlarmsModel by lazy { AlarmsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra(IntentKeys.ROOM_ID)
        presentationModel.roomName = intent.getStringExtra(IntentKeys.ROOM_NAME)
    }

    override fun onViewAttached(view: AlarmsView?) {
        super.onViewAttached(view)
        getAlarms()
        view?.setupRoomName(presentationModel.roomName)
    }

    private fun getAlarms() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getAlarms(this, presentationModel.roomId)
    }

    override fun onGetAlarmsSuccess(alarms: Alarms) {
        view?.stopProgressDialog()
        view?.setupAlarmsDrawables(alarms.monoxide, alarms.dioxide)
    }

    override fun onGetAlarmsError() {
        view?.stopProgressDialog()
    }
}