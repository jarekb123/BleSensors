package com.butajlo.blesensors.ui.lighttab

import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.butajlo.blesensors.rxsensor.RxSensor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class LightViewModel(rxSensor: RxSensor) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _lightData = MutableLiveData<LightData>()
    val lightData: LiveData<LightData>
        get() = _lightData

    init {
        rxSensor.observe(Sensor.TYPE_LIGHT)
            .map { LightData(it[0]) }
            .subscribeBy(
                onNext = { _lightData.value = it },
                onError = { Log.e(javaClass.simpleName, it.toString()) }
            ).addTo(subscriptions)
    }

    override fun onCleared() {
        subscriptions.clear()
    }

}