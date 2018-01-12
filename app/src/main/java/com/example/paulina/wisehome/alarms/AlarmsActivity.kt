package com.example.paulina.wisehome.alarms

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_alarms.*

@ActivityView(layout = R.layout.activity_alarms, presenter = AlarmsPresenterImpl::class)
class AlarmsActivity : NavDrawerActivity(), AlarmsView {

    @Presenter
    lateinit var presenter: AlarmsPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupAlarmsDrawables(carbonMonoxideAlarrm: Boolean, carboonDioxideAlarm: Boolean) {
        if (carbonMonoxideAlarrm) {
            carboonMonoxideLayout.background = getDrawable(R.drawable.white_round_frame_red_borders)
            carbonMonoxideImage.setImageDrawable(getDrawable(R.drawable.danger))
        } else {
            carboonMonoxideLayout.background = getDrawable(R.drawable.white_round_frame_green_borders)
            carbonMonoxideImage.setImageDrawable(getDrawable(R.drawable.no_danger))
        }

        if (carboonDioxideAlarm) {
            carbonDioxideLayout.background = getDrawable(R.drawable.white_round_frame_red_borders)
            carbonDioxideImage.setImageDrawable(getDrawable(R.drawable.danger))
        } else {
            carbonDioxideLayout.background = getDrawable(R.drawable.white_round_frame_green_borders)
            carbonDioxideImage.setImageDrawable(getDrawable(R.drawable.no_danger))
        }
    }

    override fun setupRoomName(roomName : String){
        roomNameTextView.setText(roomName)
    }
}