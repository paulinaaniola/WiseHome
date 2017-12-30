package com.example.paulina.wisehome.adddevice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.transportobjects.Room
import kotlinx.android.synthetic.main.item_room.view.*

internal class RoomsAdapter(private val context: Context) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    var rooms: List<Room> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_room_to_choose, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roomNameTextView.text = rooms[position].roomName
        holder.roomNameLayout.setOnClickListener({ v -> (context as AddDeviceView).displayRoomConfigurationDialog(rooms[position]) })
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomNameTextView = view.roomNameTextView
        val roomNameLayout = view.roomNameLayout
    }
}