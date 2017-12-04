package com.example.paulina.wisehome.lights

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_lights.*
import kotlinx.android.synthetic.main.color_picker_layout.*
import kotlinx.android.synthetic.main.saturation_bar_layout.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_lights, presenter = LightsPresenterImpl::class)
class LightsActivity : NavDrawerActivity(), LightsView {

    private val lightsAdapter: LightsAdapter = LightsAdapter()

    @Presenter
    lateinit var presenter: LightsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(toolbar, " 123")
        setupLightsList()
        setupColorPicker()
    }

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    private fun setupLightsList() {
        lightRecyclerView.layoutManager = LinearLayoutManager(this)
        lightRecyclerView.adapter = lightsAdapter
    }

    override fun setLights(lights: List<LightBulb>) {
        lightsAdapter.lightBulbs = lights
    }

    private fun setupColorPicker() {
        colorPicker.showOldCenterColor = false
        colorPicker.addSaturationBar(saturationBar)
    }
}