package com.example.paulina.wisehome.service

import android.widget.Toast
import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.base.ApplicationContext
import com.example.paulina.wisehome.model.businessobjects.BlindDirection
import com.example.paulina.wisehome.model.businessobjects.ResponseErrorMessage
import com.example.paulina.wisehome.model.transportobjects.*
import com.example.paulina.wisehome.model.utils.ResUtil
import com.example.paulina.wisehome.model.utils.ToastUtil
import com.example.paulina.wisehome.service.receivers.*
import com.google.gson.Gson
import retrofit2.HttpException
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception

object ServiceManager {

    fun getRooms(receiver: GetRoomsReciever) {
        setupRequest(ServiceProvider
                .roomsService
                .getRooms(),
                Action1 {
                    receiver.onGetRoomsSuccess(it as List<Room>)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetRoomsError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getUnconfigDevices(receiver: GetUnconfigDevicesReciever) {
        setupRequest(ServiceProvider
                .unconfigDeviceService
                .getUnconfigDevices(),
                Action1 {
                    receiver.onGetUnconfigDevicesSucces(it as List<UnconfigDevice>)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetUnconfigDevicesError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun highlightDevice(receiver: PostHighlightSelectedDevice, deviceId: String, power: Boolean) {
        setupRequest(ServiceProvider
                .unconfigDeviceService
                .highlightDevice(deviceId, power),
                Action1 {
                    receiver.onHighlightDeviceSuccess(it as Boolean)
                },
                Action1 {
                    handleError(it)
                    receiver.onHighlightDeviceError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun addDeviceToRoom(receiver: AddDeviceToRoomReciever, roomId: String, deviceName: String, mac: String) {
        setupRequest(ServiceProvider
                .unconfigDeviceService
                .addDeviceToRoom(roomId, deviceName, mac),
                Action1 {
                    receiver.onAddDeviceToRoomSuccess()
                },
                Action1 {
                    handleError(it)
                    receiver.onAddDeviceToRoomError()
                },
                Action0 { Timber.e("OnCompleted") })
    }


    fun addNewRoom(receiver: AddRoomReciever, roomName: String) {
        setupRequest(ServiceProvider
                .roomsService
                .addNewRoom(roomName),
                Action1 {
                    receiver.onAddRoomSuccess()
                },
                Action1 {
                    handleError(it)
                    receiver.onAddRoomError()
                },
                Action0 { Timber.e("OnCompleted") })
    }


    fun getLights(receiver: GetLightsReciever, roomId: String) {
        setupRequest(ServiceProvider
                .lightsService
                .getLights(roomId),
                Action1 {
                    receiver.onGetLightsSuccess(it as Lights)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetLightsError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun changeLightColor(receiver: PostChangeLightColorReciver, roomId: String, color: RGBColor) {
        setupRequest(ServiceProvider
                .lightsService
                .changeLightColor(roomId, color),
                Action1 {
                    receiver.onChangeLightColorSuccess()
                },
                Action1 {
                    handleError(it)
                    receiver.onChangeLightColorError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun turnOnOffLight(receiver: PostTurnOnOffLightReciever, lightId: String, power: Boolean) {
        setupRequest(ServiceProvider
                .lightsService
                .turnOnOffLight(lightId, power),
                Action1 {
                    receiver.onTurnOnOffSucces()
                },
                Action1 {
                    handleError(it)
                    receiver.onTurnOnOffError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getBlinds(receiver: GetBlindsReciever, roomId: String) {
        setupRequest(ServiceProvider
                .blindsService
                .getBlinds(roomId),
                Action1 {
                    receiver.onGetBlindsSuccess(it as List<Blind>)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetBlindsError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun changeBlindState(receiver: PostChangeBlindState, blindId: String, direction: BlindDirection) {
        setupRequest(ServiceProvider
                .blindsService
                .changeBlindState(blindId, direction),
                Action1 {
                    receiver.onChangeBlindStateSuccess()
                },
                Action1 {
                    handleError(it)
                    receiver.onChangeBlindsStateError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getWeather(receiver: GetWeatherReciever, roomId: String) {
        setupRequest(ServiceProvider
                .weatherService
                .getWeather(roomId),
                Action1 {
                    receiver.onGetWeatherSuccess(it as Weather)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetWeatherError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getAlarms(receiver: GetAlarmsReciever, roomId: String) {
        setupRequest(ServiceProvider
                .alarmsService
                .getAlarms(roomId),
                Action1 {
                    receiver.onGetAlarmsSuccess(it as Alarms)
                },
                Action1 {
                    handleError(it)
                    receiver.onGetAlarmsError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    private fun setupRequest(observable: Observable<*>, onNext: Action1<Any>, onError: Action1<Throwable>, onCompleted: Action0): Subscription {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onCompleted)
    }

    fun handleError(error: Throwable) {
        val msg = getResponseMessage(error)

        if (error is HttpException) {
            val context = ApplicationContext.appContext

            if (error.code() != 401 && error.code() != 404) {
                if (error.code() == 409 && msg != null && !msg.isEmpty()) {
                    ToastUtil.show(context, msg, Toast.LENGTH_LONG)
                } else {
                    ToastUtil.show(context, ResUtil.getString(R.string.sth_wrong_check_internet_connection), Toast.LENGTH_LONG)
                }
            } else if (error.code() == 401) {
                ToastUtil.show(context, ResUtil.getString(R.string.authorization_error), Toast.LENGTH_LONG)
            }
        }
    }

    private fun getResponseMessage(error: Throwable): String? {
        var msg = ""
        if (error is HttpException && error.response() != null && error.response().errorBody() != null) {
            try {
                val response = error.response()
                msg = response.errorBody()?.string() ?: ""
                msg = Gson().fromJson(msg, ResponseErrorMessage::class.java).message
            } catch (e: Exception) {
                Timber.e("unable to get error message in ServiceManager::handleError()")
            }
        }
        return msg
    }
}