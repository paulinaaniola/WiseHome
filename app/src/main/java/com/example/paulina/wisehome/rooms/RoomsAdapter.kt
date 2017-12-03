package com.example.paulina.wisehome.rooms

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.DeviceType
import com.example.paulina.wisehome.model.businessobjects.Room
import kotlinx.android.synthetic.main.item_room.view.*


internal class RoomsAdapter(private val context: Context) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    var rooms: List<Room> = emptyList<Room>()
        set(value) {
            field = value
            initializeRoomCollapsedList(value)
            notifyDataSetChanged()
        }
    lateinit var isRoomCollapsedList: MutableList<Boolean>
    var isInitializing: Boolean =true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roomNameTextView.text = rooms[position].name
        setupDeviceTypesList(holder, rooms[position].listOfDeviceGroupTypes)
        holder.roomNameLayout.setOnClickListener({ v -> expandOrCollapseList(position, holder) })
    }

    private fun expandOrCollapseList(position: Int, holder: ViewHolder) {
        val isRoomCollapsed: Boolean = isRoomCollapsedList[position]
        (context as RoomsView).devicesLayoutExpandCollapse(isRoomCollapsed, holder.expandableLayout)
        (context as RoomsView).arrowAnimation(isRoomCollapsed, holder.arrowUpImageView, holder.arroDownImageView)
        isRoomCollapsedList[position] = !isRoomCollapsed
    }

    private fun setupDeviceTypesList(holder: ViewHolder, listOfDeviceTypes: List<DeviceType>) {
        holder.deviceTypesRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.deviceTypesRecyclerView.adapter = DeviceTypesAdapter(listOfDeviceTypes, context)
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
    }

    private fun initializeRoomCollapsedList(roomList: List<Room>) {
        isRoomCollapsedList = mutableListOf<Boolean>()
        for (i in roomList.listIterator()) {
            isRoomCollapsedList.add(true)
        }
    }
}