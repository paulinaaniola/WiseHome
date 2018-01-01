package com.example.paulina.wisehome.addroom

import android.content.Intent
import android.os.Bundle
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseActivity
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.rooms.RoomsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_room.*


@ActivityView(layout = R.layout.activity_add_room, presenter = AddRoomPresenterImpl::class)
class AddRoomActivity : BaseActivity(), AddRoomView {
    @Presenter
    lateinit var presenter: AddRoomPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupButton()
    }

    private fun setupButton() {
        addButton.setOnClickListener({ onAddButtonClick() })
    }

    private fun onAddButtonClick() {
        val roomName = roomNameEditText.text
        if (roomName.isEmpty()) {
            roomNameEditText.error = ResUtil.getString(R.string.blank_edit_text_error)
        } else {
            roomNameEditText.clearError()
            presenter.addRoom(roomName.toString())
        }
    }

    override fun navigateToRooms() {
        startActivity(Intent(this, RoomsActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}