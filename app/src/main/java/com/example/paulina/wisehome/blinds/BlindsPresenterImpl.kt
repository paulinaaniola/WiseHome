package com.example.paulina.wisehome.blinds

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.base.IntentKeys
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.businessobjects.BlindState
import com.example.paulina.wisehome.model.transportobjects.Blind
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.GetBlindsReciever
import com.example.paulina.wisehome.service.receivers.PostChangeBlindState
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.google.firebase.database.*
import timber.log.Timber


class BlindsPresenterImpl : BaseAbstractPresenter<BlindsView>(), BlindsPresenter, GetBlindsReciever, PostChangeBlindState {

    private val presentationModel: BlindsModel by lazy { BlindsModel() }

    private lateinit var mDatabase: DatabaseReference
    private lateinit var test: DatabaseReference
    private lateinit var devicesStates: DatabaseReference
    private lateinit var blindState: DatabaseReference

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra(IntentKeys.ROOM_ID)
        presentationModel.roomName = intent.getStringExtra(IntentKeys.ROOM_NAME)
    }

    override fun onViewAttached(view: BlindsView?) {
        super.onViewAttached(view)
        setupDatabaseReferences()
        getBlinds()
        view?.setupRoomName(presentationModel.roomName)
    }

    private fun setupDatabaseReferences() {
        mDatabase = FirebaseDatabase.getInstance().getReference()
        test = mDatabase.child("test")
        devicesStates = test.child("devicesStates")
        blindState = devicesStates.child("blindState")
    }

    private fun getBlinds() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getBlinds(this, presentationModel.roomId)
        //  onGetBlindsSuccess(createDummyBlinds())
    }

    override fun onGetBlindsSuccess(blinds: List<Blind>) {
        view?.stopProgressDialog()
        view?.setBlinds(blinds)
        setupBlindStateChangeListener(blinds)
    }

    override fun onGetBlindsError() {
        view?.stopProgressDialog()
    }

    private fun setupBlindStateChangeListener(blinds: List<Blind>) {
        for (blind in blinds) {
            blindState.child(blind._id).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val id = dataSnapshot.key
                    val state = dataSnapshot.child("state").value
                    view?.updateBlindState(id, state.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.d(LOG_TAG, databaseError.message)
                }
            })
        }
    }

    override fun changeBlindDirection(direction: BlindDirection, blindId: String) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        // onChangeBlindStateSuccess()
        ServiceManager.changeBlindState(this, blindId, direction)
    }

    override fun onChangeBlindStateSuccess() {
        view?.stopProgressDialog()
    }

    override fun onChangeBlindsStateError() {
        view?.stopProgressDialog()
    }

    private fun createDummyBlinds(): List<Blind> {
        var blinds: MutableList<Blind> = mutableListOf()
        blinds.add(Blind("1", "Blind1", BlindState.CLOSED))
        blinds.add(Blind("2", "Blind2", BlindState.OPENED))
        blinds.add(Blind("3", "Blind3", BlindState.MOVING))
        blinds.add(Blind("4", "Blind4", BlindState.PARTLY_CLOSED))
        blinds.add(Blind("5", "Blind5", BlindState.CLOSED))
        return blinds
    }
}