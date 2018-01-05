package com.example.paulina.wisehome.service.receivers


interface PostHighlightSelectedDevice {
    fun onHighlightDeviceSuccess(power: Boolean)
    fun onHighlightDeviceError()
}
