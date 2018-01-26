package com.example.paulina.wisehome.lights

import android.content.Intent
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.BaseAbstractPresenter
import com.example.paulina.wisehome.base.IntentKeys
import com.example.paulina.wisehome.model.transportobjects.LightBulb
import com.example.paulina.wisehome.model.transportobjects.Lights
import com.example.paulina.wisehome.model.transportobjects.RGBColor
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.service.ServiceManager
import com.example.paulina.wisehome.service.receivers.GetLightsReciever
import com.example.paulina.wisehome.service.receivers.PostChangeLightColorReciver
import com.example.paulina.wisehome.service.receivers.PostTurnOnOffLightReciever
import com.example.paulina.wisehome.service.receivers.SetAutomaticWorkReciever
import com.github.mikephil.charting.charts.Chart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class LightsPresenterImpl : BaseAbstractPresenter<LightsView>(), LightsPresenter, GetLightsReciever, PostChangeLightColorReciver, PostTurnOnOffLightReciever, SetAutomaticWorkReciever {

    private val presentationModel: LightsModel by lazy { LightsModel() }

    override fun initExtras(intent: Intent) {
        presentationModel.roomId = intent.getStringExtra(IntentKeys.ROOM_ID)
        presentationModel.roomName = intent.getStringExtra(IntentKeys.ROOM_NAME)
    }

    override fun onViewAttached(view: LightsView?) {
        super.onViewAttached(view)
        setupDatabaseReferences()
        getLight()
        view?.setupRoomName(presentationModel.roomName)
    }

    private fun setupDatabaseReferences() {
        val pm = presentationModel
        pm.mDatabase = FirebaseDatabase.getInstance().getReference()
        pm.devicesStates = pm.mDatabase.child("devicesStates")
        pm.lightBulbPower = pm.devicesStates.child("lightBulbPower")
    }

    private fun getLight() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
       // ServiceManager.getLights(this, presentationModel.roomId)
        onGetLightsSuccess(createDummyLights())
    }

    override fun onGetLightsSuccess(lights: Lights) {
        view?.stopProgressDialog()
        presentationModel.isAutomaticMode = lights.automaticMode
        view?.setAutomaticModeSwitch(lights.automaticMode)
        view?.setLights(lights)
        setupLightStateChangeListener(lights.lightBulbs)
    }

    override fun onGetLightsError() {
        view?.stopProgressDialog()
    }

    private fun createDummyLights(): Lights {
        val light : Lights
        val lighBulbs : ArrayList<LightBulb> = ArrayList()
        lighBulbs.add(LightBulb("d", "Light 1", false))
        lighBulbs.add(LightBulb("d", "Light 2", true))
        lighBulbs.add(LightBulb("d", "Light 3", false))
        light = Lights(false, RGBColor("100","120","100"), lighBulbs)
        return light
    }

    private fun setupLightStateChangeListener(lightBulbs: List<LightBulb>) {
        for (lightBulb in lightBulbs) {
            presentationModel.lightBulbPower.child(lightBulb._id).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val id = dataSnapshot.key
                    val state = dataSnapshot.child("isPoweredOn").getValue<Boolean>(Boolean::class.java)
                    view?.updateLighBulbsState(id, state)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.d(Chart.LOG_TAG, databaseError.message)
                }
            })
        }
    }

    override fun changeLightColor(color: RGBColor) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        // onChangeLightColorSuccess()
        ServiceManager.changeLightColor(this, presentationModel.roomId, color)
    }

    override fun onChangeLightColorError() {
        Timber.e("color not changed")
        view?.stopProgressDialog()
    }

    override fun onChangeLightColorSuccess() {
        Timber.e("Color Changes")
        view?.stopProgressDialog()
    }

    override fun turnOnOffLight(lightId: String, power: Boolean) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        // onTurnOnOffSucces()
        ServiceManager.turnOnOffLight(this, lightId, power)
    }

    override fun onTurnOnOffError() {
        view?.stopProgressDialog()
        Timber.e("BŁĄD")
    }

    override fun onTurnOnOffSucces() {
        view?.stopProgressDialog()
        Timber.e("POSZŁOOO")
    }

    override fun getRoomId(): String {
        return presentationModel.roomId
    }

    override fun setAutomaticMode(isAutomaticMode: Boolean) {
        ServiceManager.setAutomaticMode(this, presentationModel.roomId, isAutomaticMode)
        presentationModel.isAutomaticMode = isAutomaticMode
    }

    override fun isAutomaticMode(): Boolean {
        return presentationModel.isAutomaticMode
    }

    override fun onSetAutomaticWorkError() {
    }

    override fun onSetAutomaticWorkSuccess() {
    }
}