package com.example.paulina.wisehome.blinds

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.BlindState
import com.example.paulina.wisehome.model.transportobjects.Blind
import com.example.paulina.wisehome.model.utils.ResUtil


class BlindsPresenterImpl : BaseAbstractPresenter<BlindsView>(), BlindsPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: BlindsView?) {
        super.onViewAttached(view)
        getBlinds()
    }

    private fun getBlinds() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onGetBlindsSucces(createDummyBlinds())
    }

    override fun onGetBlindsSucces(blinds: List<Blind>) {
        view?.stopProgressDialog()
        view?.setBlinds(blinds)
    }

    override fun onGetBlindsError() {
        view?.stopProgressDialog()
    }

    private fun createDummyBlinds(): List<Blind> {
        var blinds: MutableList<Blind> = mutableListOf()
        blinds.add(Blind("1", "Blind1", BlindState.CLOSED))
        blinds.add(Blind("2", "Blind2", BlindState.OPEN))
        blinds.add(Blind("3", "Blind3", BlindState.MOVING))
        blinds.add(Blind("4", "Blind4", BlindState.PARTLY_CLOSE))
        blinds.add(Blind("5", "Blind5", BlindState.CLOSED))
        return blinds
    }
}