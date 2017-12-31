package com.example.paulina.wisehome.colorlist

import android.content.Intent
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.model.businessobjects.Color


class ColorListPresenterImpl : BaseAbstractPresenter<ColorListView>(), ColorListPresenter {

    private val presentationModel: ColorListModel by lazy { ColorListModel() }

    override fun initExtras(intent: Intent) {
    }

    override fun createColorsList(): List<Color> {
        var colors: MutableList<Color> = mutableListOf()
        colors.add(presentationModel.colorStudy)
        colors.add(presentationModel.colorRelax)
        colors.add(presentationModel.colorWork)
        return colors
    }
}
