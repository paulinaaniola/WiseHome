package com.example.paulina.wisehome.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.businessobjects.NavDrawerItemType


open class NavDrawerAdapter(context: Context) :
        ArrayAdapter<NavDrawerItemType>(context, R.layout.item_nav_drawer) {

    var items: MutableList<NavDrawerItemType> = mutableListOf()

    override fun getView(position: Int, dropDownItem: View?, parent: ViewGroup): View {
        var navDrawerItem: View?
        val item = items[position]
        val inflater = LayoutInflater.from(context)
        navDrawerItem = inflater.inflate(R.layout.item_nav_drawer, parent, false)
        val itemNameTextView = navDrawerItem.findViewById<TextView>(R.id.itemNameTextView)
        itemNameTextView.text = item.toStringLocale()
        return navDrawerItem
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): NavDrawerItemType? {
        return items[position]
    }
}