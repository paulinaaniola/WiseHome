package com.example.paulina.wisehome.service

import com.example.paulina.wisehome.service.api.*

object ServiceProvider {

    var BASE_URL = "http://192.168.43.197:4000"

    var roomsService: RoomsApi = ServiceFactory.createRetrofitService(RoomsApi::class.java, BASE_URL, false, false)

    var unconfigDeviceService: UnconfigDevicesApi = ServiceFactory.createRetrofitService(UnconfigDevicesApi::class.java, BASE_URL, false, false)

    var lightsService: LightsApi = ServiceFactory.createRetrofitService(LightsApi::class.java, BASE_URL, false, false)

    var blindsService: BlindsApi = ServiceFactory.createRetrofitService(BlindsApi::class.java, BASE_URL, false, false)

    var weatherService: WeatherApi = ServiceFactory.createRetrofitService(WeatherApi::class.java, BASE_URL, false, false)
}
