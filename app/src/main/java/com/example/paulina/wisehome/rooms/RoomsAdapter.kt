package com.example.paulina.wisehome.rooms

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roomNameTextView.text = rooms[position].roomName
        setupDeviceTypesList(holder, rooms[position])
        setupArrowLayout(holder, position)
    }

    private fun setupArrowLayout(holder: ViewHolder, position: Int) {
        if (rooms[position].deviceGroups.isEmpty()) {
            holder.arrowsLayout.visibility = View.GONE
        } else {
            holder.arrowsLayout.visibility = View.VISIBLE
            holder.roomNameLayout.setOnClickListener({ v -> expandOrCollapseList(holder) })
        }
    }

    private fun expandOrCollapseList(holder: ViewHolder) {
        val isRoomCollapsed: Boolean = holder.expandableLayout.visibility == View.GONE
        if (isRoomCollapsed) {
            (context as RoomsView).expandDevicesLayout(holder.expandableLayout, holder.arrowUpImageView, holder.arroDownImageView)
        } else {
            (context as RoomsView).collapseDevicesLayout(holder.expandableLayout, holder.arrowUpImageView, holder.arroDownImageView)
        }
    }

    private fun setupDeviceTypesList(holder: ViewHolder, room: Room) {
        holder.deviceTypesRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.deviceTypesRecyclerView.adapter = DeviceTypesAdapter(room, context)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomNameTextView = view.roomNameTextView
        val deviceTypesRecyclerView = view.deviceTypesRecyclerView
        val roomNameLayout = view.roomNameLayout
        val expandableLayout = view.expandableLayout
        val arrowUpImageView = view.arrowUpImageView
        val arroDownImageView = view.arrowDownImageView
        val arrowsLayout = view.arrowsLayout
    }
}