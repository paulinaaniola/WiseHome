package com.example.paulina.wisehome.lights

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.service.receivers.GetLightsReciever

class LightsPresenterImpl : BaseAbstractPresenter<LightsView>(), LightsPresenter, GetLightsReciever {

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
        //ServiceManager.getLights(this, presentationModel.roomId)
    }

    override fun onGetLightsError() {
    }

    private fun createDummyLights(): Lights {
        val lightBulbs: MutableList<LightBulb> = ArrayList<LightBulb>()
        lightBulbs.add(LightBulb( 1.toString(), "Light 1",true))
        lightBulbs.add(LightBulb( 1.toString(),"Light 2", false))
        lightBulbs.add(LightBulb( 1.toString(), "Light 3",false))
        lightBulbs.add(LightBulb( 1.toString(), "Light 4", true))
        val lights = Lights(1, lightBulbs)
        return lights
    }

    override fun onGetLightsSuccess(lights: Lights) {
        view?.setLights(lights.lightBulbs)
    }
}