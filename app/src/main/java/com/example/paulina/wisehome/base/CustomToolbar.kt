package com.example.paulina.wisehome.base

import com.yalantis.jellytoolbar.widget.JellyToolbar

class CustomToolbar{
    fun setupToolbar(jellyToolbar: JellyToolbar, title: String) {
        val toolbar = jellyToolbar.toolbar
        if(toolbar != null) {
            toolbar.setPadding(0, 0, 0, 0)
        }
    }
}