package com.example.paulina.wisehome.rooms

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.alarms.AlarmsActivity
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.blinds.BlindsActivity
import com.example.paulina.wisehome.lights.LightsActivity
import com.example.paulina.wisehome.model.transportobjects.Room
import com.example.paulina.wisehome.model.utils.AnimUtils
import com.example.paulina.wisehome.weather.WeatherActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_rooms.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_rooms, presenter = RoomsPresenterImpl::class)
class RoomsActivity : NavDrawerActivity(), RoomsView {

    private val roomsAdapter: RoomsAdapter = RoomsAdapter(this)

    @Presenter
    lateinit var presenter: RoomsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(toolbar, " 123")
        setupRoomsList()
    }

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    private fun setupRoomsList() {
        roomsRecyclerView.layoutManager = LinearLayoutManager(this)
        roomsRecyclerView.adapter = roomsAdapter
    }

    override fun setRooms(rooms: List<Room>) {
        roomsAdapter.rooms = rooms
    }

    override fun onLightsClick(roomId: String) {
        startActivity(Intent(this, LightsActivity::class.java).putExtra("room_id", roomId))
    }

    override fun onBlindsClick(roomId: String) {
        startActivity(Intent(this, BlindsActivity::class.java).putExtra("room_id", roomId))
    }

    override fun onAlarmSensorsClick(roomId: String) {
        startActivity(Intent(this, AlarmsActivity::class.java).putExtra("room_id", roomId))
    }

    override fun onWeatherSensorsClick(roomId: String) {
        startActivity(Intent(this, WeatherActivity::class.java).putExtra("room_id", roomId))
    }

    override fun expandDevicesLayout(viewToAnim: View, arrowUp: View, arrowDown: View){
        val animDuration = presenter.getAnimDuration()
        AnimUtils.expand(animDuration, viewToAnim)
        AnimUtils.fadeIn(animDuration, arrowUp)
        AnimUtils.fadeOut(animDuration, arrowDown)
    }

    override fun collapseDevicesLayout(viewToAnim: View, arrowUp: View, arrowDown: View){
        val animDuration = presenter.getAnimDuration()
        AnimUtils.collapse(animDuration, viewToAnim)
        AnimUtils.fadeIn(animDuration, arrowDown)
        AnimUtils.fadeOut(animDuration, arrowUp)
    }
}