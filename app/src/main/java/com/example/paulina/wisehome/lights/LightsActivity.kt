package com.example.paulina.wisehome.lights

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.larswerkman.holocolorpicker.ColorPicker
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_lights.*
import kotlinx.android.synthetic.main.color_picker_layout.*
import kotlinx.android.synthetic.main.saturation_bar_layout.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_lights, presenter = LightsPresenterImpl::class)
class LightsActivity : NavDrawerActivity(), LightsView {

    private val lightsAdapter: LightsAdapter = LightsAdapter(this)

    @Presenter
    lateinit var presenter: LightsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(toolbar, " 123")
        setupLightsList()
        setupColorPicker()
        setSelectedColorTextView()
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


    private fun setSelectedColorTextView() {
        colorPicker.color = java.lang.Long.parseLong("ECE247", 16).toInt()
        colorPicker.onColorChangedListener = ColorPicker.OnColorChangedListener { color ->
            colorInt.setText(Integer.toString(color))
            val stringHex = java.lang.Integer.toHexString(color)
            colorHex.setText(stringHex)

            val red : Int = Color.red(color)
            val green : Int= Color.green(color)
            val blue : Int= Color.blue(color)

            colorR.setText(red.toString())
            colorG.setText(green.toString())
            colorB.setText(blue.toString())
        }

        changeColorButton.setOnClickListener(({view -> changeLightColor() }))
    }

    private fun changeLightColor() {
        val colorInt : Int = colorPicker.color
        var rgbColor : RGBColor = RGBColor(Color.red(colorInt).toString(), Color.green(colorInt).toString(), Color.blue(colorInt).toString())
        presenter.changeLightColor(rgbColor)
    }

    override fun onBulbSwitchClick(lightId: String, isPowerOn : Boolean){
        presenter.turnOnOffLight(lightId, isPowerOn)
    }
}