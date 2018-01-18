package com.example.paulina.wisehome.weather

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.WeatherMeasurements
import com.example.paulina.wisehome.model.transportobjects.Weather
import com.example.paulina.wisehome.model.utils.ResUtil
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
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

    override fun setupRoomName(roomName: String) {
        roomNameTextView.setText(roomName)
    }

    override fun setupFeverGraph(historicMeasurements: ArrayList<WeatherMeasurements>?) {
        val humidityPoints = arrayOfNulls<DataPoint>(historicMeasurements?.size!!)
        for (i in 0 until historicMeasurements.size) {
            humidityPoints[i] = DataPoint(i.toDouble(), historicMeasurements[i].humidity.toDouble())
        }
        val seriesHumidity = LineGraphSeries<DataPoint>(humidityPoints)

        val temperaturePoints = arrayOfNulls<DataPoint>(historicMeasurements.size)
        for (i in 0 until historicMeasurements.size) {
            temperaturePoints[i] = DataPoint(i.toDouble(), historicMeasurements[i].temperature.toDouble())
        }
        val seriesTemperature = LineGraphSeries<DataPoint>(temperaturePoints)

        weatherGraphView.addSeries(seriesTemperature)
        weatherGraphView.secondScale.addSeries(seriesHumidity)
        weatherGraphView.secondScale.setMinY(60.0)
        weatherGraphView.secondScale.setMaxY(135.0)
        weatherGraphView.setBackgroundColor(ResUtil.getColor(R.color.colorPrimary)!!)

        seriesTemperature.color = ResUtil.getColor(R.color.colorLightBlue)!!
        seriesTemperature.title = "Humidity"
        seriesTemperature.isDrawDataPoints = true
        seriesTemperature.thickness = 8

        seriesHumidity.color = ResUtil.getColor(R.color.colorLightRed)!!
        seriesHumidity.title = ResUtil.getString(R.string.temperature)
        seriesHumidity.isDrawDataPoints = true
        seriesHumidity.thickness = 8
        weatherGraphView.gridLabelRenderer.verticalLabelsSecondScaleColor = ResUtil.getColor(R.color.colorLightRed)!!

        val staticLabelsFormatter = StaticLabelsFormatter(weatherGraphView)
        val labels = arrayOfNulls<String>(historicMeasurements.size)
        for (i in 0 until historicMeasurements.size) {
            if (historicMeasurements[i].date.minute ==0) {
                labels[i] = historicMeasurements[i].date.toLocalTime().toString()
            } else {
                labels[i] = ""
            }
            staticLabelsFormatter.setHorizontalLabels(labels)
            weatherGraphView.gridLabelRenderer.labelFormatter = staticLabelsFormatter
        }
    }
}