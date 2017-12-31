package com.example.paulina.wisehome.colorlist

import android.support.v7.widget.LinearLayoutManager
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BasePresenter
import com.example.paulina.wisehome.base.NavDrawerActivity
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_color_list.*


@ActivityView(layout = R.layout.activity_color_list, presenter = ColorListPresenterImpl::class)
class ColorListActivity : NavDrawerActivity(), ColorListView {

    @Presenter
    lateinit var presenter: ColorListPresenter

    private lateinit var colorsAdapter: ColorsAdapter

    override fun onStart() {
        super.onStart()
        colorsAdapter = ColorsAdapter(this, presenter.createColorsList())
        setupColorsList()
    }

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    private fun setupColorsList() {
        colorListRecyclerView.layoutManager = LinearLayoutManager(this)
        colorListRecyclerView.adapter = colorsAdapter
    }

    override fun onColorClick(color: RGBColor) {
    }
}