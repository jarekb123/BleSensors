package com.butajlo.blesensors.rxsensor

import android.hardware.SensorManager
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class RxSensor(
    private val sensorManager: SensorManager,
    private val rxSensorListenerFactory: RxSensorListenerFactory
) {

    fun observe(
        sensorType: Int,
        samplingPeriodUs: Int = SensorManager.SENSOR_DELAY_NORMAL
    ): Flowable<FloatArray> {

        val sensor = sensorManager.getDefaultSensor(sensorType)
            ?: return Flowable.error<FloatArray>(NoSuchSensorException(sensorType))

        val rxSensorListener = rxSensorListenerFactory.create()

        return Flowable.create<FloatArray>({
            rxSensorListener.eventsEmitter = it
        }, BackpressureStrategy.DROP)
            .doOnSubscribe {
                sensorManager.registerListener(rxSensorListener, sensor, samplingPeriodUs)
            }
            .doOnCancel {
                sensorManager.unregisterListener(rxSensorListener)
            }


    }


}