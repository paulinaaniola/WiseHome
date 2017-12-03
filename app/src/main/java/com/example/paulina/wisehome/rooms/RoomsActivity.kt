package com.example.paulina.wisehome.rooms

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.lights.LightsActivity
import com.example.paulina.wisehome.model.businessobjects.Room
import com.example.paulina.wisehome.model.utils.AnimUtils
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

    override fun onLightsClick() {
        startActivity(Intent(this, LightsActivity::class.java))
    }

    override fun onBlindsClick() {
    }

    override fun onAlarmSensorsClick() {
    }

    override fun onWeatherSensorsClick() {
    }

    override fun devicesLayoutExpandCollapse(isCollapsed: Boolean, viewToAnim: View) {
        val animDuration = presenter.getAnimDuration()
        if (isCollapsed) {
            AnimUtils.expand(animDuration, viewToAnim)
        } else {
            AnimUtils.collapse(animDuration, viewToAnim)
        }
    }

    override    fun arrowAnimation(isCollapsed: Boolean, arrowUp: View, arrowDown : View) {
        val animDuration = presenter.getAnimDuration()
        if(isCollapsed) {
            AnimUtils.fadeIn(animDuration, arrowUp)
            AnimUtils.fadeOut(animDuration, arrowDown)
        } else {
            AnimUtils.fadeIn(animDuration, arrowDown)
            AnimUtils.fadeOut(animDuration, arrowUp)
        }
    }
}