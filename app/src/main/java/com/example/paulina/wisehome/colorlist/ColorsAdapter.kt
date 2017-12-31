package com.example.paulina.wisehome.colorlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.Color
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.example.paulina.wisehome.model.utils.ColorUtil
import kotlinx.android.synthetic.main.item_predefined_color.view.*


internal class ColorsAdapter(val context: Context, val colors: List<Color>) : RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_predefined_color, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorNameTextView.text = colors[position].name
        setColorView(holder, position)
        holder.colorLayout.setOnClickListener({ onColorClick(position) })
    }

    private fun setColorView(holder: ViewHolder, position: Int) {
        val colorHex: String = "#" + ColorUtil.intToHex(colors[position].value)
        holder.colorView.setBackgroundColor(android.graphics.Color.parseColor(colorHex))
    }

    private fun onColorClick(position: Int) {
        val rgbColor: RGBColor = ColorUtil.intToRgb(colors[position].value)
        (context as ColorListView).onColorClick(rgbColor)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorLayout = view.colorLayout
        val colorNameTextView = view.colorNameTextView
        val colorView = view.colorView
    }
}
