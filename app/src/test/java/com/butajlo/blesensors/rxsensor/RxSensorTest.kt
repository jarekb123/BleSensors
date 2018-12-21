package com.butajlo.blesensors.rxsensor

import android.hardware.Sensor
import android.hardware.SensorManager
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class RxSensorTest {

    companion object {
        private const val SENSOR_TYPE = 0
    }

    private val sensor = mock<Sensor>()
    private val sensorManager = mock<SensorManager> {
        on(it.getDefaultSensor(SENSOR_TYPE)).thenReturn(sensor)
    }
    private val rxSensorListener = mock<RxSensorListener>()
    private val rxSensorListenerFactory = mock<RxSensorListenerFactory> {
        on(it.create()).thenReturn(rxSensorListener)
    }
    private val rxSensor = RxSensor(sensorManager, rxSensorListenerFactory)

    @Test
    fun `observe() should register sensor listener on subscribe`() {
        val disposable = rxSensor.observe(SENSOR_TYPE)
            .subscribe()

        verify(sensorManager).registerListener(
            rxSensorListener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        disposable.dispose()
        verify(sensorManager).unregisterListener(rxSensorListener)
    }
}