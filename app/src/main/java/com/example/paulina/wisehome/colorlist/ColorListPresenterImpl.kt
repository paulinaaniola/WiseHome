package com.example.paulina.wisehome.colorlist

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.Color
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.PostChangeLightColorReciver


class ColorListPresenterImpl : BaseAbstractPresenter<ColorListView>(), ColorListPresenter, PostChangeLightColorReciver {

    private val presentationModel: ColorListModel by lazy { ColorListModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra("roomId")
    }

    override fun createColorsList(): List<Color> {
        var colors: MutableList<Color> = mutableListOf()
        colors.add(presentationModel.colorStudy)
        colors.add(presentationModel.colorRelax)
        colors.add(presentationModel.colorWork)
        return colors
    }

    override fun changeLightColor(color: RGBColor) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.changeLightColor(this, presentationModel.roomId, color)
        onChangeLightColorSuccess()
    }

    override fun onChangeLightColorSuccess() {
        view?.stopProgressDialog()
    }

    override fun onChangeLightColorError() {
        view?.stopProgressDialog()
    }
}
