package com.example.paulina.wisehome.base

import android.content.Context
import android.support.multidex.BuildConfig
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import io.paperdb.Paper
import timber.log.Timber

class ApplicationContext : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        MultiDex.install(applicationContext)
        Utils.init(applicationContext)
        Paper.init(mAppContext)
//        Fabric.with(this, Crashlytics())

        if (BuildConfig.DEBUG) run {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        private var mAppContext: Context? = null

        var appContext: Context?
            get() = mAppContext
            set(mAppContext) {
                ApplicationContext.mAppContext = mAppContext
            }
    }
}