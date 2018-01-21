package com.example.paulina.wisehome.weather

import android.text.format.DateFormat
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.NewWeather
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_weather.*
import java.util.*


@ActivityView(layout = R.layout.activity_weather, presenter = WeatherPresenterImpl::class)
class WeatherActivity : NavDrawerActivity(), WeatherView {

    @Presenter
    lateinit var presenter: WeatherPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupWeatherState(weather: NewWeather) {
        humidityTextView.setText(weather.humidity.toString())
        temperatureTextView.setText(weather.temperature.toString())
    }

    override fun setupRoomName(roomName: String) {
        roomNameTextView.setText(roomName)
    }

    override fun setupCharts(historicMeasurements: List<WeatherMeasurements>) {
        if (!historicMeasurements.isEmpty()) {
            setupTemperatureChart(historicMeasurements)
            setupHumidityChart(historicMeasurements)
        }
    }

    fun setupTemperatureChart(historicMeasurements: List<WeatherMeasurements>) {
        temperatureChart.setScaleEnabled(true)
        temperatureChart.getAxisRight().setEnabled(false)
        temperatureChart.legend.isEnabled = false
        temperatureChart.getDescription().setEnabled(false)
        val xAxis = temperatureChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setTextColor(R.color.colorAccent)
       // xAxis.setLabelCount(8, true)
        val a = Date(historicMeasurements[0].createdAt).hours
        xAxis.valueFormatter = object : IAxisValueFormatter {

            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                //val time = historicMeasurements[value.toInt()].createdAt
                //return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
                return getDate(value.toLong())
            }
        }

        val values = ArrayList<Entry>()
        for (i in 0 until historicMeasurements.size - 1) {
            values.add(Entry(historicMeasurements[i].createdAt.toFloat(), historicMeasurements[i].temperature.toFloat()))
        }
        val set = LineDataSet(values, "")
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.setColors(resources.getColor(R.color.colorLightRed))
        var data = LineData(set)
        temperatureChart.data = data
    }

    fun setupHumidityChart(historicMeasurements: List<WeatherMeasurements>) {
        humidityChart.setScaleEnabled(true)
        humidityChart.getAxisRight().setEnabled(false)
        humidityChart.legend.isEnabled = false
        humidityChart.getDescription().setEnabled(false)
        val xAxis = humidityChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setTextColor(R.color.colorAccent)
        xAxis.setLabelCount(8, true)
//        xAxis.valueFormatter = object : IAxisValueFormatter {
//
//            override fun getFormattedValue(value: Float, axis: AxisBase): String {
//                val time = historicMeasurements[value.toInt()].createdAt.toLocalTime()
//                return time.format(DateTimeFormatter.ofPattern("HH:mm"))
//            }
//        }

        val values = ArrayList<Entry>()
        for (i in 0 until historicMeasurements.size - 1) {
            values.add(Entry(historicMeasurements[i].createdAt.toFloat(), historicMeasurements[i].humidity.toFloat()))
        }
        val set = LineDataSet(values, "")
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.setColors(resources.getColor(R.color.colorLightBlue))
        var data = LineData(set)
        humidityChart.data = data
    }

    private fun getDate(time: Long): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time
        return DateFormat.format("HH:mm", cal).toString()
    }
}