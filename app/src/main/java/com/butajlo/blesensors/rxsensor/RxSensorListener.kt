package com.butajlo.blesensors.rxsensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import io.reactivex.FlowableEmitter

class RxSensorListener: SensorEventListener {

    lateinit var eventsEmitter: FlowableEmitter<FloatArray>

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit

    override fun onSensorChanged(event: SensorEvent) {
        eventsEmitter.onNext(event.values)
    }

}