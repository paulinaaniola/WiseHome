package com.example.paulina.wisehome.base

import com.blankj.utilcode.util.SizeUtils
import com.yalantis.jellytoolbar.widget.JellyToolbar

class CustomToolbar{
    fun setupToolbar(jellyToolbar: JellyToolbar, title: String) {
        val toolbar = jellyToolbar.toolbar
        if(toolbar != null) {
            toolbar.setPadding(0, SizeUtils.dp2px(24f), SizeUtils.dp2px(24f), 0)
        }
    }
}