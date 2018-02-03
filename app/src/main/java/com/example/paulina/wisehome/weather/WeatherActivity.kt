package com.example.paulina.wisehome.weather

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
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


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
        temperatureChart.labelFor
        val yAxis = temperatureChart.axisLeft
        yAxis.granularity = 1f
        val xAxis = temperatureChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setLabelCount(9, true)
        xAxis.setTextColor(R.color.colorAccent)

        val tenDaysAgoDate: Long = LocalDateTime.now().minusDays(10).toEpochSecond(ZoneOffset.ofHours(1))
        xAxis.valueFormatter = object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                val correctTimeStamp = value.toLong() + tenDaysAgoDate
                val correctDate = LocalDateTime.ofEpochSecond(correctTimeStamp, 0, ZoneOffset.ofHours(1)).toLocalTime()
                return correctDate.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
        }

        val values = ArrayList<Entry>()
        for (i in 0 until historicMeasurements.size) {
            val timeDifference = historicMeasurements[i].createdAt - tenDaysAgoDate
            values.add(Entry(timeDifference.toFloat(), historicMeasurements[i].temperature.toFloat()))
        }

        val set = LineDataSet(values, "")
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.setColors(resources.getColor(R.color.colorLightRed))
        set.lineWidth = 2f
        var data = LineData(set)
        temperatureChart.data = data
        temperatureChart.invalidate()
    }

    fun setupHumidityChart(historicMeasurements: List<WeatherMeasurements>) {
        humidityChart.setScaleEnabled(true)
        humidityChart.getAxisRight().setEnabled(false)
        humidityChart.legend.isEnabled = false
        humidityChart.getDescription().setEnabled(false)
        humidityChart.labelFor
        val xAxis = humidityChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setLabelCount(9, true)
        xAxis.setTextColor(R.color.colorAccent)

        val tenDaysAgoDate: Long = LocalDateTime.now().minusDays(10).toEpochSecond(ZoneOffset.ofHours(1))
        xAxis.valueFormatter = object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                val correctTimeStamp = value.toLong() + tenDaysAgoDate
                val correctDate = LocalDateTime.ofEpochSecond(correctTimeStamp, 0, ZoneOffset.ofHours(1)).toLocalTime()
                return correctDate.format(DateTimeFormatter.ofPattern("HH:mm"))
            }
        }
        val values = ArrayList<Entry>()
        for (i in 0 until historicMeasurements.size) {
            val timeDifference = historicMeasurements[i].createdAt - tenDaysAgoDate
            values.add(Entry(timeDifference.toFloat(), historicMeasurements[i].humidity.toFloat()))
        }

        val set = LineDataSet(values, "")
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.setColors(resources.getColor(R.color.colorLightBlue))
        set.lineWidth = 2f
        var data = LineData(set)
        humidityChart.data = data
        humidityChart.invalidate()
    }
}