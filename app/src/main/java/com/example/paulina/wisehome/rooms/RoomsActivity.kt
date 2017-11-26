package com.example.paulina.wisehome.rooms

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.Room
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_rooms.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_rooms, presenter = RoomsPresenterImpl::class)
class RoomsActivity : NavDrawerActivity(), RoomsView {

    private val roomsAdapter : RoomsAdapter = RoomsAdapter()

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

    override fun setRooms(rooms : List<Room>){
        roomsAdapter.rooms = rooms
    }
}