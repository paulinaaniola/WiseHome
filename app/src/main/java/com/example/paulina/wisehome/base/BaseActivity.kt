package com.example.paulina.wisehome.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.service.ServiceProvider
import com.github.mikephil.charting.charts.Chart
import com.google.firebase.database.*
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var progress: ProgressDialog? = null
    private var dialogCloseButton: ImageView? = null
    private var isCloseableOnBackButton = false
    private lateinit var mDatabase: DatabaseReference
    private lateinit var serverIp: DatabaseReference

    override val activityContext: Context
        get() = this

    override fun onStart() {
        super.onStart()
        initExtras(providePresenter())
        setupDatabaseReferences()
        setupServerIpAdressChangeListener()
    }

    override fun startProgressDialog(message: String?) {
        if ((progress == null || !progress!!.isShowing) && !isFinishing) {
            progress = ProgressDialog.show(this@BaseActivity, getString(R.string.progress_loading_text),
                    message, true)
            Handler().postDelayed({
                if (progress != null && !isFinishing && progress!!.isShowing) {
                    progress!!.cancel()
                }
            }, 10000)
        }
    }

    override fun stopProgressDialog() {
        if (progress != null && !isFinishing && progress!!.isShowing) {
            progress?.dismiss()
        }
    }

    internal fun initExtras(presenter: BasePresenter?) {
        if (presenter != null) {
            presenter!!.initExtras(intent)
        }
    }

    abstract fun providePresenter(): BasePresenter?

    fun performOnBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (!isCloseableOnBackButton && dialogCloseButton != null && dialogCloseButton!!.visibility == View.VISIBLE) {
            dialogCloseButton!!.performClick()
        } else {
            super.onBackPressed()
        }
    }

    fun setDialogCloseable(dialogCloseButton: ImageView) {
        this.dialogCloseButton = dialogCloseButton
    }

    fun setCloseBackButton(isCloseableOnBackButton: Boolean) {
        this.isCloseableOnBackButton = isCloseableOnBackButton
    }

    private fun setupDatabaseReferences() {
        mDatabase = FirebaseDatabase.getInstance().getReference()
        serverIp = mDatabase.child("serverIP")
    }

    private fun setupServerIpAdressChangeListener() {
        serverIp.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val ip = dataSnapshot.child("ip").value
                ServiceProvider.BASE_URL = "http://" + ip as String + ":4000"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Timber.d(Chart.LOG_TAG, databaseError.message)
            }
        })
    }
}