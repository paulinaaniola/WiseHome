package com.example.paulina.wisehome.service

import com.example.paulina.wisehome.service.api.*

object ServiceProvider {

    var BASE_URL = "http://123:4000"
        set(value) {
            field = value
            setupServices()
        }

    lateinit var roomsService: RoomsApi
    lateinit var unconfigDeviceService: UnconfigDevicesApi
    lateinit var lightsService: LightsApi
    lateinit var blindsService: BlindsApi
    lateinit var weatherService: WeatherApi
    lateinit var alarmsService: AlarmsApi
    lateinit var loginService: LoginApi

    private fun setupServices() {
        roomsService = ServiceFactory.createRetrofitService(RoomsApi::class.java, BASE_URL, false, false)

        unconfigDeviceService = ServiceFactory.createRetrofitService(UnconfigDevicesApi::class.java, BASE_URL, false, false)

        lightsService = ServiceFactory.createRetrofitService(LightsApi::class.java, BASE_URL, false, false)

        blindsService = ServiceFactory.createRetrofitService(BlindsApi::class.java, BASE_URL, false, false)

        weatherService = ServiceFactory.createRetrofitService(WeatherApi::class.java, BASE_URL, false, false)

        alarmsService = ServiceFactory.createRetrofitService(AlarmsApi::class.java, BASE_URL, false, false)

        loginService = ServiceFactory.createRetrofitService(LoginApi::class.java, BASE_URL, false, false)
    }
}
