package com.butajlo.blesensors.rxsensor

import android.hardware.SensorEvent
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.FlowableEmitter
import org.junit.Test

class RxSensorObservableTest {

    companion object {
        private val EVENT_VALUES = arrayOf(0.5f, 32.0f, 24.3f).toFloatArray()
    }

    private val emitter = mock<FlowableEmitter<FloatArray>>()
    private val rxSensorListener = RxSensorListener().apply {
        eventsEmitter = emitter
    }


    private val sensorEvent = mock<SensorEvent> {
        try {
            SensorEvent::class.java.getField("values").apply {
                isAccessible = true
                set(it, EVENT_VALUES)
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    @Test
    fun `should emit values on sensor changed`() {
        rxSensorListener.onSensorChanged(sensorEvent)
        verify(emitter).onNext(EVENT_VALUES)
    }

}