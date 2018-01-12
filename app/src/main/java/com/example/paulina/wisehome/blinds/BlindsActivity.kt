package com.example.paulina.wisehome.blinds

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.businessobjects.BlindState
import com.example.paulina.wisehome.model.transportobjects.Blind
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_blinds.*
import kotlinx.android.synthetic.main.toolbar.*


@ActivityView(layout = R.layout.activity_blinds, presenter = BlindsPresenterImpl::class)
class BlindsActivity : NavDrawerActivity(), BlindsView {

    @Presenter
    lateinit var presenter: BlindsPresenter


    private val blindsAdapter: BlindsAdapter = BlindsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar(toolbar, " 123")
        setupRoomsList()
    }

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    private fun setupRoomsList() {
        blindsRecyclerView.layoutManager = LinearLayoutManager(this)
        blindsRecyclerView.adapter = blindsAdapter
    }

    override fun setBlinds(blinds: List<Blind>) {
        blindsAdapter.blinds = blinds
    }

    override fun onChangeBlindDirectionClick(direction: BlindDirection, blindId: String) {
        presenter.changeBlindDirection(direction, blindId)
    }

    override fun updateBlindState(id: String, state: String) {
        var blindState: BlindState = BlindState.OPENED
        when (state) {
            getString(R.string.closed) -> blindState = BlindState.CLOSED
            getString(R.string.opened) -> blindState = BlindState.OPENED
            getString(R.string.moving) -> blindState = BlindState.MOVING
            getString(R.string.partly_close_underscore) -> blindState = BlindState.PARTLY_CLOSED
        }

        blindsAdapter.updateBlindState(id, blindState)
    }

    override fun setupRoomName(roomName : String){
        roomNameTextView.setText(roomName)
    }
}