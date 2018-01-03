package com.example.paulina.wisehome.lights

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetLightsReciever
import com.example.paulina.wisehome.service.receivers.PostChangeLightColorReciver
import com.example.paulina.wisehome.service.receivers.PostTurnOnOffLightReciever
import timber.log.Timber

class LightsPresenterImpl : BaseAbstractPresenter<LightsView>(), LightsPresenter, GetLightsReciever, PostChangeLightColorReciver, PostTurnOnOffLightReciever {

    private val presentationModel: LightsModel by lazy { LightsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("room_id")
    }

    override fun onViewAttached(view: LightsView?) {
        super.onViewAttached(view)
        getLight()
    }

    private fun getLight() {
        onGetLightsSuccess(createDummyLights())
        // ServiceManager.getLights(this, presentationModel.roomId)
    }

    override fun onGetLightsError() {
    }

    private fun createDummyLights(): Lights {
        val lightBulbs: MutableList<LightBulb> = ArrayList<LightBulb>()
        lightBulbs.add(LightBulb(1.toString(), "Light 1", true))
        lightBulbs.add(LightBulb(1.toString(), "Light 2", false))
        lightBulbs.add(LightBulb(1.toString(), "Light 3", false))
        lightBulbs.add(LightBulb(1.toString(), "Light 4", true))
        val lights = Lights(RGBColor("", "", ""), lightBulbs)
        return lights
    }

    override fun onGetLightsSuccess(lights: Lights) {
        view?.setLights(lights.lightBulbs)
    }

    override fun changeLightColor(color: RGBColor) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onChangeLightColorSuccess()
        // ServiceManager.changeLightColor(this, presentationModel.roomId, color)
    }

    override fun onChangeLightColorError() {
        Timber.e("color not changed")
        view?.stopProgressDialog()
    }

    override fun onChangeLightColorSuccess() {
        Timber.e("Color Changes")
        view?.stopProgressDialog()
    }

    override fun turnOnOffLight(lightId: String, power: Boolean) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        onTurnOnOffSucces()
        // ServiceManager.turnOnOffLight(this, presentationModel.roomId, lightId, power)
    }

    override fun onTurnOnOffError() {
        view?.stopProgressDialog()
        Timber.e("BŁĄD")
    }

    override fun onTurnOnOffSucces() {
        view?.stopProgressDialog()
        Timber.e("POSZŁOOO")
    }

    override fun getRoomId(): String {
        return presentationModel.roomId
    }
}