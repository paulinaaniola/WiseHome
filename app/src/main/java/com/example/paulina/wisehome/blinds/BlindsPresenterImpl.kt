package com.example.paulina.wisehome.blinds

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.businessobjects.BlindState
import com.example.paulina.wisehome.model.transportobjects.Blind
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetBlindsReciever
import com.example.paulina.wisehome.service.receivers.PostChangeBlindState


class BlindsPresenterImpl : BaseAbstractPresenter<BlindsView>(), BlindsPresenter, GetBlindsReciever, PostChangeBlindState {

    private val presentationModel: BlindsModel by lazy { BlindsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("room_id")
    }

    override fun onViewAttached(view: BlindsView?) {
        super.onViewAttached(view)
        getBlinds()
    }

    private fun getBlinds() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        // ServiceManager.getBlinds(this, presentationModel.roomId)
        onGetBlindsSuccess(createDummyBlinds())
    }

    override fun onGetBlindsSuccess(blinds: List<Blind>) {
        view?.stopProgressDialog()
        view?.setBlinds(blinds)
    }

    override fun onGetBlindsError() {
        view?.stopProgressDialog()
    }

    override fun changeBlindDirection(direction: BlindDirection) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onChangeBlindStateSuccess()
        // ServiceManager.changeBlindState(this, presentationModel.roomId, direction)
    }

    override fun onChangeBlindStateSuccess() {
        view?.stopProgressDialog()
    }

    override fun onChangeBlindsStateError() {
        view?.stopProgressDialog()
    }

    private fun createDummyBlinds(): List<Blind> {
        var blinds: MutableList<Blind> = mutableListOf()
        blinds.add(Blind("1", "Blind1", BlindState.CLOSED))
        blinds.add(Blind("2", "Blind2", BlindState.OPENED))
        blinds.add(Blind("3", "Blind3", BlindState.MOVING))
        blinds.add(Blind("4", "Blind4", BlindState.PARTLY_CLOSED))
        blinds.add(Blind("5", "Blind5", BlindState.CLOSED))
        return blinds
    }
}