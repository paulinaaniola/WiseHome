package com.example.paulina.wisehome.lights

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import kotlinx.android.synthetic.main.item_light_bulb.view.*

internal class LightsAdapter(val context: Context) : RecyclerView.Adapter<LightsAdapter.ViewHolder>() {

    var lightBulbs: List<LightBulb> = emptyList<LightBulb>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_light_bulb, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lightNameTextView.text = lightBulbs[position].name
        holder.bulbSwitch.isChecked = lightBulbs[position].isPoweredOn
        holder.bulbSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            (context as LightsView).onBulbSwitchClick(lightBulbs[position]._id, isChecked)
        })
    }

    override fun getItemCount(): Int {
        return lightBulbs.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lightNameTextView = view.lighNameTextView
        val bulbSwitch = view.bulbSwitch
    }
}
