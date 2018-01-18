package com.example.paulina.wisehome.lights

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.CompoundButton
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.colorlist.ColorListActivity
import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.example.paulina.wisehome.model.utils.ColorUtil
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
        setupButtons()
    }

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    private fun setupLightsList() {
        lightRecyclerView.layoutManager = LinearLayoutManager(this)
        lightRecyclerView.adapter = lightsAdapter
    }

    override fun setLights(lights: Lights) {
        lightsAdapter.automaticMode = lights.automaticMode
        setupAutomaticModeSwitch(lights.automaticMode)
        lightsAdapter.lightBulbs = lights.lightBulbs
        colorPicker.color = ColorUtil.rgbToInt(lights.currentColor)
    }

    private fun setupColorPicker() {
        colorPicker.showOldCenterColor = false
        colorPicker.addSaturationBar(saturationBar)
    }

    private fun setupButtons() {

        colorListButton.setOnClickListener({
            startActivity(Intent(this, ColorListActivity::class.java)
                    .putExtra("roomId", presenter.getRoomId())
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
        })
        changeColorButton.setOnClickListener({ changeLightColor() })
        automaticModeSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            presenter.setAutomaticMode(isChecked)
            lightsAdapter.automaticMode = isChecked
        })
    }

    private fun changeLightColor() {
        val colorInt: Int = colorPicker.color
        presenter.changeLightColor(ColorUtil.intToRgb(colorInt))
        val red: Int = Color.red(colorInt)
        val green: Int = Color.green(colorInt)
        val blue: Int = Color.blue(colorInt)
        val rgb: RGBColor = ColorUtil.intToRgb(colorInt)
        val colorInt1: Int = ColorUtil.rgbToInt(rgb)
    }

    private fun setupAutomaticModeSwitch(automaticMode: Boolean){
        automaticModeSwitch.isChecked = automaticMode
    }

    override fun onBulbSwitchClick(lightId: String, isPowerOn: Boolean) {
        if (!presenter.isAutomaticMode()) {
            presenter.turnOnOffLight(lightId, isPowerOn)
        }
    }

    override fun updateLighBulbsState(id: String, isPoweredOn: Boolean?) {
        lightsAdapter.updateLightsState(id, isPoweredOn)
    }

    override fun setAutomaticModeSwitch(isPoweredOn: Boolean){
        automaticModeSwitch.isChecked = isPoweredOn
    }
    override fun setupRoomName(roomName: String) {
        roomNameTextView.setText(roomName)
    }
}