package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.transportobjects.Weather
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_weather.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


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

    override fun setupRoomName(roomName: String) {
        roomNameTextView.setText(roomName)
    }

    override fun setupTemperatureChart(historicMeasurements: List<WeatherMeasurements>) {

        val xAxis = temperatureChart.getXAxis()
        xAxis.setTextColor(R.color.colorAccent)
        val firstTime = historicMeasurements[0].date.toLocalTime()
        val lastTime = historicMeasurements[historicMeasurements.size - 1].date.toLocalTime()

        xAxis.valueFormatter = object : IAxisValueFormatter {

            private val mFormat = SimpleDateFormat("dd MMM HH:mm")

            override fun getFormattedValue(value: Float, axis: AxisBase): String {

                val millis = TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }

        val values = ArrayList<Entry>()
        for(i in 0 until historicMeasurements.size){
       //     values.add(Entry(historicMeasurements[0].date., historicMeasurements[0].temperature.toFloat()))
        }
        val set1 = LineDataSet(values, "DataSet 1")
        var data = LineData(set1)
        temperatureChart.data = data

//        listOfDates.add(firstTime as AxisValue)
//        var hoursStep = 2;
//        while (firstTime.plusHours(hoursStep.toLong()).isBefore(lastTime)) {
//            val newTime : LocalTime = firstTime.plusHours(hoursStep.toLong())
//            listOfDates.add(newTime as AxisValue)
//            hoursStep = hoursStep + 2;
//        }
//        listOfDates.add(lastTime as AxisValue)
    }
}