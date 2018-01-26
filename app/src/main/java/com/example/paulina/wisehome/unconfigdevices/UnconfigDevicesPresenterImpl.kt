package com.example.paulina.wisehome.unconfigdevices

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.transportobjects.UnconfigDevice
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetUnconfigDevicesReciever
import com.example.paulina.wisehome.service.receivers.PostHighlightSelectedDevice


class UnconfigDevicesPresenterImpl : BaseAbstractPresenter<UnconfigDevicesView>(), UnconfigDevicesPresenter, GetUnconfigDevicesReciever, PostHighlightSelectedDevice {

    override fun initExtras(intent: Intent) {
    }

    private val presentationModel: UnconfigDevicesModel by lazy { UnconfigDevicesModel() }

    override fun onViewAttached(view: UnconfigDevicesView?) {
        super.onViewAttached(view)
        getUnconfigDevices()
    }

    fun getUnconfigDevices() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
       // ServiceManager.getUnconfigDevices(this)
        onGetUnconfigDevicesSucces(createDummyDevices())
    }

    override fun onGetUnconfigDevicesSucces(devices: List<UnconfigDevice>) {
        view?.stopProgressDialog()
        view?.setDevices(devices)
        view?.setupEmptyView(devices.isEmpty())
    }

    override fun onGetUnconfigDevicesError() {
        view?.stopProgressDialog()
    }

    override fun saveSelectedDevice(selectedDevice: UnconfigDevice) {
        presentationModel.selectedDevice = selectedDevice
    }

    override fun highlightSelectedDevice(power: Boolean) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        //ServiceManager.highlightDevice(this, presentationModel.selectedDevice.mac, power)
         onHighlightDeviceSuccess(true)
    }

    override fun onHighlightDeviceSuccess(power: Boolean) {
        if (power == true) {
            view?.displayDeviceHighlightedDialog(presentationModel.selectedDevice)
        }
        view?.stopProgressDialog()
    }

    override fun onHighlightDeviceError() {
        view?.stopProgressDialog()
    }

    override fun getSelectedDevice(): UnconfigDevice {
        return presentationModel.selectedDevice
    }

    private fun createDummyDevices(): List<UnconfigDevice> {
        val devices: MutableList<UnconfigDevice> = mutableListOf()
        devices.add(UnconfigDevice("1", "1", DeviceType.LIGHTS))
        devices.add(UnconfigDevice("2", "1", DeviceType.LIGHTS))
        devices.add(UnconfigDevice("7", "1", DeviceType.LIGHTS))
        devices.add(UnconfigDevice("5", "1", DeviceType.BLINDS))
        devices.add(UnconfigDevice("3", "1", DeviceType.BLINDS))
        devices.add(UnconfigDevice("4", "1", DeviceType.ALARM_SENSORS))
        devices.add(UnconfigDevice("6", "1", DeviceType.WEATHER_SENSORS))
        return devices
    }
}