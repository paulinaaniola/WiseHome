package com.example.paulina.wisehome.weather

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter

class WeatherPresenterImpl : BaseAbstractPresenter<WeatherView>(), WeatherPresenter {

    private val presentationModel: WeatherModel by lazy { WeatherModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("room_id")
    }
}
