package com.example.paulina.wisehome.weather

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.base.IntentKeys
import com.example.paulina.wisehome.model.businessobjects.NewWeather
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.transportobjects.Weather
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetWeatherReciever
import org.threeten.bp.LocalDateTime

class WeatherPresenterImpl : BaseAbstractPresenter<WeatherView>(), WeatherPresenter, GetWeatherReciever {

    private val presentationModel: WeatherModel by lazy { WeatherModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra(IntentKeys.ROOM_ID)
        presentationModel.roomName = intent.getStringExtra(IntentKeys.ROOM_NAME)
    }

    override fun onViewAttached(view: WeatherView?) {
        super.onViewAttached(view)
        getWeather()
        view?.setupRoomName(presentationModel.roomName)
    }

    fun getWeather() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        //ServiceManager.getWeather(this, presentationModel.roomId)
        onGetWeatherSuccess(Weather("24", "11"))
    }

    override fun onGetWeatherSuccess(weather: Weather) {
        view?.stopProgressDialog()
        view?.setupWeatherState(weather)
        view?.setupCharts(createDummyWeather().historicMeasurements)
    }

    override fun onGetWeatherError() {
        view?.stopProgressDialog()
    }

    private fun createDummyWeather(): NewWeather {
        val historicMeasurements: ArrayList<WeatherMeasurements> = arrayListOf()
        for(i in 0..11) {
            historicMeasurements.add(WeatherMeasurements(20, 100, LocalDateTime.now()))
            historicMeasurements.add(WeatherMeasurements(30, 110, LocalDateTime.now().plusMinutes(30)))
            historicMeasurements.add(WeatherMeasurements(30, 110, LocalDateTime.now().plusMinutes(45)))
            historicMeasurements.add(WeatherMeasurements(32, 120, LocalDateTime.now().plusMinutes(60)))
            historicMeasurements.add(WeatherMeasurements(22, 130, LocalDateTime.now().plusMinutes(90)))
            historicMeasurements.add(WeatherMeasurements(21, 120, LocalDateTime.now().plusMinutes(120)))
            historicMeasurements.add(WeatherMeasurements(22, 140, LocalDateTime.now().plusMinutes(150)))
            historicMeasurements.add(WeatherMeasurements(23, 100, LocalDateTime.now().plusMinutes(180)))
            historicMeasurements.add(WeatherMeasurements(20, 100, LocalDateTime.now().plusMinutes(210)))
            historicMeasurements.add(WeatherMeasurements(19, 100, LocalDateTime.now().plusMinutes(240)))
            historicMeasurements.add(WeatherMeasurements(20, 100, LocalDateTime.now().plusMinutes(270)))
            historicMeasurements.add(WeatherMeasurements(22, 100, LocalDateTime.now().plusMinutes(300)))
            historicMeasurements.add(WeatherMeasurements(23, 100, LocalDateTime.now().plusMinutes(330)))
            historicMeasurements.add(WeatherMeasurements(24, 100, LocalDateTime.now().plusMinutes(360)))
            historicMeasurements.add(WeatherMeasurements(15, 100, LocalDateTime.now().plusMinutes(390)))
            historicMeasurements.add(WeatherMeasurements(10, 100, LocalDateTime.now().plusMinutes(450)))
            historicMeasurements.add(WeatherMeasurements(10, 100, LocalDateTime.now().plusMinutes(510)))
            historicMeasurements.add(WeatherMeasurements(10, 100, LocalDateTime.now().plusMinutes(570)))
        }
        return NewWeather(100, 200, historicMeasurements)
    }
}
