package com.example.paulina.wisehome.lights

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.LightBulb
import com.example.paulina.wisehome.model.businessobjects.Lights

class LightsPresenterImpl : BaseAbstractPresenter<LightsView>(), LightsPresenter {

    private val presentationModel: LightsModel by lazy { LightsModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: LightsView?) {
        super.onViewAttached(view)
        getLight()
    }

    private fun getLight() {
        onGetLightSucces(createDummyLights())
    }

    private fun onGetLightSucces(light: Lights) {
        if (view != null) {
            view?.setLights(light.listOfLightBulb)
        }
    }

    private fun createDummyLights(): Lights {
        val lightBulbs: MutableList<LightBulb> = ArrayList<LightBulb>()
        lightBulbs.add(LightBulb("Light 1", 1, true))
        lightBulbs.add(LightBulb("Light 2", 1, false))
        lightBulbs.add(LightBulb("Light 3", 1, false))
        lightBulbs.add(LightBulb("Light 4", 1, true))
        val lights = Lights(1, lightBulbs)
        return lights
    }
}