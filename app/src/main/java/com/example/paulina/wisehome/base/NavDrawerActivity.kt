package com.example.paulina.wisehome.base

import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.rooms.RoomsActivity
import com.yalantis.jellytoolbar.widget.JellyToolbar
import kotlinx.android.synthetic.main.activity_rooms.*
import kotlinx.android.synthetic.main.toolbar.*


abstract class NavDrawerActivity : BaseActivity() {

    protected var shouldCreateNavDrawer: Boolean = false
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var closeable: Boolean = false
    private var isInitializing = true
    // private var drawerAdapter: NavDrawerAdapter? = null

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        shouldCreateNavDrawer = true
        onCreateDrawer()
        isInitializing = false
    }

    protected fun onCreateDrawer() {
        setSupportActionBar(toolbar.toolbar)
        if (shouldCreateNavDrawer) {
            setDrawerToggle()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationIcon(toolbar.toolbar as Toolbar)
//            setupDrawerList()
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }

    private fun setDrawerToggle() {
        var drawer = drawerToggle
        drawer = object : ActionBarDrawerToggle(this, drawerLayout, 0, 0) {
            override fun onDrawerClosed(view: View?) {
                //do nothing
            }

            override fun onDrawerOpened(drawerView: View?) {
                //do nothing
            }
        }
        drawerLayout.addDrawerListener(drawer)
        drawer.setDrawerIndicatorEnabled(false)
    }

    private fun setNavigationIcon(toolbar: Toolbar) {
        toolbar.setNavigationIcon(if (closeable) R.mipmap.close else R.mipmap.menu)
        toolbar.setNavigationOnClickListener({ v -> onNavIconClick() })
    }

    private fun onNavIconClick() {
        if (closeable) {
            startActivity(Intent(this, RoomsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_down)
        } else {
            drawerLayout.openDrawer(Gravity.LEFT)
        }
    }

    protected fun setupToolbar(toolbar: JellyToolbar, title: String) {
        CustomToolbar().setupToolbar(toolbar, title)
    }
}