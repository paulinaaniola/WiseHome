package com.example.paulina.wisehome.weather

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.base.IntentKeys
import com.example.paulina.wisehome.model.businessobjects.NewWeather
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.receivers.GetWeatherReciever
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

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
        // ServiceManager.getWeather(this, presentationModel.roomId)
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
        val minutes: Long = LocalDateTime.now().minute.toLong()
        historicMeasurements.add(WeatherMeasurements(20, 32, org.threeten.bp.LocalDateTime.now().minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 35, org.threeten.bp.LocalDateTime.now().plusHours(1).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(19, 30, org.threeten.bp.LocalDateTime.now().plusHours(2).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(18, 30, org.threeten.bp.LocalDateTime.now().plusHours(3).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(20, 28, org.threeten.bp.LocalDateTime.now().plusHours(4).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 30, org.threeten.bp.LocalDateTime.now().plusHours(5).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 25, org.threeten.bp.LocalDateTime.now().plusHours(6).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 25, org.threeten.bp.LocalDateTime.now().plusHours(7).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(19, 24, org.threeten.bp.LocalDateTime.now().plusHours(8).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(19, 30, org.threeten.bp.LocalDateTime.now().plusHours(9).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(19, 33, org.threeten.bp.LocalDateTime.now().plusHours(10).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(20, 32, org.threeten.bp.LocalDateTime.now().plusHours(11).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(20, 35, org.threeten.bp.LocalDateTime.now().plusHours(12).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 37, org.threeten.bp.LocalDateTime.now().plusHours(13).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(20, 35, org.threeten.bp.LocalDateTime.now().plusHours(14).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(19, 34, org.threeten.bp.LocalDateTime.now().plusHours(15).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 34, org.threeten.bp.LocalDateTime.now().plusHours(16).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(22, 33, org.threeten.bp.LocalDateTime.now().plusHours(17).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 33, org.threeten.bp.LocalDateTime.now().plusHours(18).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(22, 32, org.threeten.bp.LocalDateTime.now().plusHours(19).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(22, 33, org.threeten.bp.LocalDateTime.now().plusHours(20).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 35, org.threeten.bp.LocalDateTime.now().plusHours(21).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 33, org.threeten.bp.LocalDateTime.now().plusHours(22).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(22, 37, org.threeten.bp.LocalDateTime.now().plusHours(23).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        historicMeasurements.add(WeatherMeasurements(21, 35, org.threeten.bp.LocalDateTime.now().plusHours(24).minusMinutes(minutes).toEpochSecond(ZoneOffset.ofHours(1))))
        return NewWeather(historicMeasurements, 21, 35)
    }
}
