package com.example.paulina.wisehome.weather

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.NewWeather
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetWeatherReciever
import org.threeten.bp.ZoneOffset

class WeatherPresenterImpl : BaseAbstractPresenter<WeatherView>(), WeatherPresenter, GetWeatherReciever {

    private val presentationModel: WeatherModel by lazy { WeatherModel() }

    override fun initExtras(intent: Intent) {
        // presentationModel.roomId = intent.getStringExtra(IntentKeys.ROOM_ID)
        //presentationModel.roomName = intent.getStringExtra(IntentKeys.ROOM_NAME)
        presentationModel.roomName = "kds"
    }

    override fun onViewAttached(view: WeatherView?) {
        super.onViewAttached(view)
        getWeather()
        view?.setupRoomName(presentationModel.roomName)
    }

    fun getWeather() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        //ServiceManager.getWeather(this, presentationModel.roomId)
        onGetWeatherSuccess(createDummyWeathet())
    }

    override fun onGetWeatherSuccess(weather: NewWeather) {
        view?.stopProgressDialog()
        view?.setupWeatherState(weather)
        view?.setupCharts(weather.historicMeasurements)
    }

    override fun onGetWeatherError() {
        view?.stopProgressDialog()
    }

    private fun createDummyWeathet(): NewWeather {
        val historicMeasurements: ArrayList<WeatherMeasurements> = arrayListOf()
        historicMeasurements.add(WeatherMeasurements(20, 80, org.threeten.bp.LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(10, 90, org.threeten.bp.LocalDateTime.now().plusMinutes(15).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(30, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(20).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(30).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(5, 70, org.threeten.bp.LocalDateTime.now().plusMinutes(40).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(10, 86, org.threeten.bp.LocalDateTime.now().plusMinutes(45).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(3, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(60).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(70).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(2, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(85).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(110).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(19, 110, org.threeten.bp.LocalDateTime.now().plusMinutes(200).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(300).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(20, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(310).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(24, 180, org.threeten.bp.LocalDateTime.now().plusMinutes(320).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(360).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(400).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(13, 90, org.threeten.bp.LocalDateTime.now().plusMinutes(500).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(600).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(13, 30, org.threeten.bp.LocalDateTime.now().plusMinutes(700).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(800).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(0, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(900).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(22, 20, org.threeten.bp.LocalDateTime.now().plusMinutes(1000).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(23, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(1200).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(20, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(1230).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(21, 0, org.threeten.bp.LocalDateTime.now().plusMinutes(1235).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(22, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(1240).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(20, 80, org.threeten.bp.LocalDateTime.now().plusMinutes(1250).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(20, 50, org.threeten.bp.LocalDateTime.now().plusMinutes(1255).toEpochSecond(ZoneOffset.UTC)))
        historicMeasurements.add(WeatherMeasurements(21, 50, org.threeten.bp.LocalDateTime.now().plusMinutes(1270).toEpochSecond(ZoneOffset.UTC)))
        return NewWeather(historicMeasurements, 100, 100)
    }
}
