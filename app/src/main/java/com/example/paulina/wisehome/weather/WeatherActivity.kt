package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.transportobjects.Weather
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_weather.*

@ActivityView(layout = R.layout.activity_weather, presenter = WeatherPresenterImpl::class)
class WeatherActivity : NavDrawerActivity(), WeatherView {

    @Presenter
    lateinit var presenter: WeatherPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupWeatherState(weather: Weather) {
        humidityTextView.setText(weather.humidity)
        temperatureTextView.setText(weather.temperature)
    }

    override fun setupRoomName(roomName : String){
        roomNameTextView.setText(roomName)
    }
}